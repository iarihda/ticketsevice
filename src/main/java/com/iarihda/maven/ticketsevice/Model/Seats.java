package com.iarihda.maven.ticketsevice.Model;

/**
 * Transaction entity to maintain and manage the seat availability of a show
 * @author Adhirai Manickam
 */
public class Seats {

	private int showId;
	private int availableSeats;
	private boolean[][] seatArray;
	
	/**
	 * Constructor
	 * @param show - Id of the show for which the seats are maintained
	 * @param capacity - initial available seats is the capacity of the screen
	 * @param rows - no. of rows
	 * @param columns - no. of columns
	 */
	Seats(int show, int capacity, int rows, int columns){
		showId = show;
		availableSeats = capacity;
		seatArray = new boolean[rows][columns];
	}
	
	int getShowId() {
		return showId;
	}
	
	int getAvailabileSeats() {
		return availableSeats;
	}
	
	boolean[][] getSeats(){
		return seatArray;
	}
	
	void setId(int show) {
		showId = show;
	}
	
	void setAvailableSeats(int count) {
		availableSeats = count;
	}
	
	void setSeatArray(boolean[][] newSeatArr) {
		seatArray = newSeatArr;
	}
}
