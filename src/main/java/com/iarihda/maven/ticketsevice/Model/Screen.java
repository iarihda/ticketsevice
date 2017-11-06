package com.iarihda.maven.ticketsevice.Model;

/**
 * Setup entity for a screen
 * @author Adhirai Manickam
 */
public class Screen {
	
	private int id;
	private int capacity;
	private int size;
	private boolean isActive;
	
	/**
	 * Constructor
	 * @param screenId - screen number or id 
	 * @param noOfSeats - capacity (total number of seats) of the screen
	 * @param screenSize - diagonal measure of the screen in inches (to differentiate between IMAX and normal screens)
	 * @param status - boolean value to denote whether the screen is used or not
	 */
	Screen(int screenId,int noOfSeats,int screenSize,boolean status){
		id = screenId;
		capacity = noOfSeats;
		size = screenSize;
		isActive = status;
	}
	
	/**
	 * @return the screen id
	 */
	int getId() {
		return id;
	}

	/**
	 * @return the screen capacity
	 */
	int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the screen size
	 */
	int getSize() {
		return size;
	}
	
	/**
	 * @return status (active or not)
	 */
	boolean isActive() {
		return isActive;
	}
	
	/**
	 * Used to change the id after the screen object is created.
	 * @param screenId
	 */
	void setId(int screenId) {
		id = screenId;
	}
	
	/**
	 * Used to change the no. of seats after the screen object is created.
	 * @param noOfSeats
	 */
	void setCapacity(int noOfSeats) {
		capacity = noOfSeats;
	}
	
	/**
	 * Used to change the size of the screen after the screen object is created.
	 * @param screenSize
	 */
	void setSize(int screenSize) {
		size = screenSize;
	}
	
	/**
	 * Change the status of the screen from active to inactive and vice versa
	 */
	void changeStatus() {
		isActive = !isActive;
	}

}
