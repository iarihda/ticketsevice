package com.iarihda.maven.ticketsevice.model;

/**
 * Transaction entity to maintain and manage the seat availability of a show
 * @author Adhirai Manickam
 */
public class Seats {

	private Show show;
	private int availableSeats;
	private boolean[][] seatArray;
	private int[] rowCount;
	
	/**
	 * Constructor
	 * @param show - Id of the show for which the seats are maintained
	 * @param capacity - initial available seats is the capacity of the screen
	 * @param rows - no. of rows
	 * @param columns - no. of columns
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
	
	private void validateShow(Show s) throws IllegalArgumentException {
		if(s==null || !s.isActive()){
			throw new IllegalArgumentException("Show should be active");
		}
		
	}

	public Show getShow() {
		return show;
	}
	
	public int getAvailabileSeats() {
		return availableSeats;
	}
	
	public boolean[][] getSeats(){
		return seatArray;
	}
	
	public int[] getRowCount(){
		return rowCount;
	}
	
	void setShow(Show s) {
		show = s;
	}

	public void incrementSeats(int numSeats) {
		availableSeats += numSeats;		
	}
	
	public void decrementSeats(int numSeats) {
		availableSeats -= numSeats;		
	}
	
	public void updateSeatAvailability(boolean[][] newSeatArr, int[] newRowCount) {
		seatArray = newSeatArr;
		rowCount = newRowCount;
//		printSeats();
	}
	
	private void printSeats(){
		for(int i=0;i<seatArray.length;i++){
			for(int j=0;j<seatArray[i].length;j++)
				System.out.print(seatArray[i][j]+" ");
			System.out.println();
		}
	}
}