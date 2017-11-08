package com.iarihda.maven.ticketsevice.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.iarihda.maven.ticketsevice.TheatreSetup;
import com.iarihda.maven.ticketsevice.model.Bookings;
import com.iarihda.maven.ticketsevice.model.SeatHold;
import com.iarihda.maven.ticketsevice.model.Seats;

/**
 * Handles the ticket blocking and booking for a particular show
 * @author Adhirai Manickam
 */
public class BookingService implements TicketService {
	
	private Seats seats;
	private boolean[][] seatArray; //Seating Array (is true if the seat is held / reserved)
	private int[] rowAvailability; //No. of seats available in each row
	private int rows;
	private int columns;
	
	private HashMap<Integer,SeatHold> seatHoldMap; //Maps the seatHoldId with the SeatHold object.

	/**
	 * Constructor
	 * @param venue - A TheatreSetup object with movie, screen and show information
	 */
	public BookingService(TheatreSetup venue) {
		rows = venue.getRowCount();
		columns = venue.getColumnCount();
		seats = new Seats(venue.getShow().getId(),venue.getScreen().getCapacity(),rows,columns);
		seatArray = seats.getSeats();
		rowAvailability = seats.getRowCount();
		seatHoldMap = new HashMap<Integer, SeatHold>();
	}
	
	/**
	* Discards the expired holds and returns the updated number of seats that are neither held nor reserved
	* @return the number of tickets available in the venue
	*/
	public synchronized int numSeatsAvailable() {
		removeExpiredHolds();
		return seats.getAvailabileSeats();
	}

	/**
	 * Removes the SeatHold objects which has expired and updates the count of seats available
	 */
	private void removeExpiredHolds() {
		Iterator<Entry<Integer, SeatHold>> it = seatHoldMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer, SeatHold> hold = it.next();
			if(!hold.getValue().isActive()){
				removeHoldOnSeats(hold.getValue().getHeldSeats());
				seats.incrementSeats(hold.getValue().getSeatCount());
				it.remove();
			}
		}
	}

	/**
	 * Change the availability of the status to 'true' in the seating array
	 * Update the no. of free seats on that particular row
	 * @param heldSeats
	 */
	private void removeHoldOnSeats(String[] heldSeats) {
		for(String heldSeat : heldSeats){
			int r = (int)heldSeat.charAt(0)-65;
			int c = Character.getNumericValue(heldSeat.charAt(1)) - 1;
			seatArray[r][c] = false;
			rowAvailability[r]++;
		}
	}

	/**
	* Find and hold the best available seats for a customer
	* @param numSeats the number of seats to find and hold
	* @param customerEmail unique identifier for the customer
	* @return a SeatHold object identifying the specific seats and related information
	*/
	public synchronized SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		if(!validate(customerEmail) || numSeats>numSeatsAvailable() || numSeats == 0)
			return null;
		String[] seatsToBeHeld = findBestSeats(numSeats);
		seats.updateSeatAvailability(seatArray,rowAvailability);
		SeatHold seatHold = new SeatHold(numSeats, customerEmail, seatsToBeHeld);
		seatHoldMap.put(seatHold.getId(), seatHold);
		seats.decrementSeats(numSeats);
		return seatHold; 
	}

	/**
	 * Find continuous seats in one row if available
	 * Else split up and find the seats based on availability in each row
	 * @param numSeats
	 * @return array of seat numbers
	 */
	private String[] findBestSeats(int numSeats) {
		String[] seatNumbers = null;
		for(int i=rows-1;i>=0;i--){
			if(rowAvailability[i]>=numSeats) {
				seatNumbers = holdSeatsInRow(i, numSeats);
				break;
			}
		}
		if(seatNumbers==null){
			seatNumbers = splitAndReserveSeats(numSeats);
		}
		return seatNumbers;
	}

	/**
	 * Split up and find the seats based on availability in each row
	 * @param numSeats
	 * @return
	 */
	private String[] splitAndReserveSeats(int numSeats) {
		String[] seatNumbers = new String[numSeats];
		int itr = 0;
		for(int i=rows-1;i>=0&&numSeats>0;i--){
			for(int j=0;j<columns&&numSeats>0;j++){
				if(!seatArray[i][j]){
					numSeats--;
					seatArray[i][j] = true;
					rowAvailability[i]--;
					seatNumbers[itr++] = (char)(i+65)+String.valueOf(j+1);
				}
			}
		}
		return seatNumbers;
	}

	/**
	 * Hold the specified no. of vacant seats in one row
	 * @param r - row number
	 * @param numSeats - no. of seats to be held
	 * @return array of seat numbers
	 */
	private String[] holdSeatsInRow(int r, int numSeats) {
		String[] seatNumbers = new String[numSeats];
		int startIndex = -1;
		int count = 0;
		for(int i=0;i<columns && count<numSeats;i++){
			if(!seatArray[r][i]){
				if(startIndex==-1)
					startIndex = i;
				else
					count++;
			} else {
				startIndex = -1;				
			}
		}
		for(int i=startIndex;i<startIndex+numSeats;i++){
			seatArray[r][i] = true;
			seatNumbers[i-startIndex] = (char)(r+65)+String.valueOf(i+1); 
		}
		rowAvailability[r] -= numSeats;
		return seatNumbers;
	}

	/**
	* Commit seats held for a specific customer
	* @param seatHoldId the seat hold identifier
	* @param customerEmail the email address of the customer to which the seat hold is assigned
	* @return a reservation confirmation code
	*/
	public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
		if(!validate(customerEmail)) return null;
		SeatHold seatsToBeBooked = seatHoldMap.get(seatHoldId);
		if(seatsToBeBooked == null || !seatsToBeBooked.isActive())
			return null;
		seatHoldMap.remove(seatHoldId);
		Bookings newBooking = new Bookings(seatsToBeBooked, customerEmail, seats.getShowId());
		return newBooking.getConfirmationCode();
	}

	private boolean validate(String customerEmail) {
		return customerEmail==null? false : customerEmail.trim().matches("^(.+)@(.+)$");
	}

}
