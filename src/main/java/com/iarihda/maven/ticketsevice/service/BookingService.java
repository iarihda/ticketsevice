package com.iarihda.maven.ticketsevice.service;

import java.util.HashMap;

import com.iarihda.maven.ticketsevice.TheatreSetup;
import com.iarihda.maven.ticketsevice.model.SeatHold;
import com.iarihda.maven.ticketsevice.model.Seats;

public class BookingService implements TicketService {
	
	Seats seats;
	boolean[][] seatArray;
	int[] rowAvailability;
	int rows;
	int columns;
	
	HashMap<Integer,SeatHold> seatHoldMap;

	public BookingService(TheatreSetup venue) {
		rows = venue.getRowCount();
		columns = venue.getColumnCount();
		seats = new Seats(venue.getShow().getId(),venue.getScreen().getCapacity(),rows,columns);
		seatArray = seats.getSeats();
		rowAvailability = seats.getRowCount();
		seatHoldMap = new HashMap<Integer, SeatHold>();
	}
	
	public int numSeatsAvailable() {
		return seats.getAvailabileSeats();
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		if(numSeats>numSeatsAvailable())
			return null;
		String[] seatsToBeHeld = findBestSeats(numSeats);
		seats.updateSeatAvailability(seatArray,rowAvailability);
		SeatHold seatHold = new SeatHold(numSeats, customerEmail, seatsToBeHeld);
		seatHoldMap.put(seatHold.getId(), seatHold);
		return seatHold; 
	}

	private String[] findBestSeats(int numSeats) {
		String[] seatNumbers = null;
		for(int i=rows-1;i>=0;i--){
			if(rowAvailability[i]>=numSeats) {
				seatNumbers = reserveSeatsInRow(i, numSeats);
				break;
			}
		}
		if(seatNumbers==null){
			seatNumbers = splitAndReserveSeats(numSeats);
		}
		return seatNumbers;
	}

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

	private String[] reserveSeatsInRow(int r, int numSeats) {
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

	public String reserveSeats(int seatHoldId, String customerEmail) {
		// TODO Auto-generated method stub
		return null;
	}

}
