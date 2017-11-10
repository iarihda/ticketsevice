package com.iarihda.maven.ticketsevice.model;

/**
 * An entity which maintains and manages the seats in a show. It contains the following
 * Show Id – The id of the show for which the seats are managed
 * Seat Availability – The current number of vacant seats which are neither held not reserved by a customer
 * Seat Array – Array representation of the seating
 * Row Availability – No. of seats which are vacant in each row
 * @author Adhirai Manickam
 */
public class Seats {

	private Show show;
	private int availableSeats;
	private boolean[][] seatArray;
	private int[] rowCount;
	
	/**
	 * Constructor
	 * @param show
	 * @param capacity
	 * @param rows
	 * @param columns
	 * @throws IllegalArgumentException 
	 */
	public Seats(Show s, int capacity, int rows, int columns) throws IllegalArgumentException{
		validateShow(s);
		show = s;
		availableSeats = capacity;
		seatArray = new boolean[rows][columns];
		rowCount = new int[rows];
		for(int i=0;i<rows;i++)
			rowCount[i] = columns;
	}
	
	/**
	 * Validation to check if the show is not null and active
	 * @@throws IllegalArgumentException
	 */
	private void validateShow(Show s) throws IllegalArgumentException {
		if(s==null || !s.isActive()){
			throw new IllegalArgumentException("Show should be active");
		}
		
	}

	/**
	 * @return show
	 */
	public Show getShow() {
		return show;
	}
	
	/**
	 * @return no. of seats available
	 */
	public int getAvailabileSeats() {
		return availableSeats;
	}
	
	
	/**
	 * @return seat array
	 */
	public boolean[][] getSeats(){
		return seatArray;
	}
	
	
	/**
	 * @return count of vacant seats per row
	 */
	public int[] getRowCount(){
		return rowCount;
	}
	
	
	/**
	 * Change the show after the object is created
	 * @params show
	 */
	public void setShow(Show s) {
		validateShow(s);
		show = s;
	}

	/**
	 * Increment the no. of availabe seats
	 * @params new count
	 */
	public void incrementSeats(int numSeats) {
		availableSeats += numSeats;		
	}
	
	
	/**
	 * Decrement the no. of availabe seats
	 * @params new count
	 */
	public void decrementSeats(int numSeats) {
		availableSeats -= numSeats;		
	}
	
	
	/**
	 * Update the seat array and row count array
	 * @params new arrays
	 */
	public void updateSeatAvailability(boolean[][] newSeatArr, int[] newRowCount) {
		seatArray = newSeatArr;
		rowCount = newRowCount;
	}
}
