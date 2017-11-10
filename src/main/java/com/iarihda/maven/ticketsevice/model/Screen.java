package com.iarihda.maven.ticketsevice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Setup entity for a screen
 * @author Adhirai Manickam
 */
public class Screen {
	
	private int id;
	private String name;
	private int capacity;
	private int size;
	private boolean isActive;
	
	private static AtomicInteger idGenerator = new AtomicInteger(1000);
	
	/**
	 * Constructor
	 * @param name 
	 * @param noOfSeats - capacity (total number of seats) of the screen
	 * @param screenSize - diagonal measure of the screen in inches (to differentiate between IMAX and normal screens)
	 * @param status - boolean value to denote whether the screen is used or not
	 */
	public Screen(String screenName, int noOfSeats,int screenSize,boolean status){
		validateParams(screenName, noOfSeats, screenSize);
		id = idGenerator.getAndIncrement();
		name = screenName;
		capacity = noOfSeats;
		size = screenSize;
		isActive = status;
	}
	
	private void validateParams(String screenName, int noOfSeats, int screenSize) {
		if(screenName==null) 
			throw new IllegalArgumentException("Screen name cannot be null");
		if(noOfSeats<=0 || screenSize<=0 )
			throw new IllegalArgumentException("No. of seats and screen size should be a positive value");
	}
	
	/**
	 * @return the screen id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the screen capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * @return the screen size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return screen name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return status (active or not)
	 */
	public boolean isActive() {
		return isActive;
	}
	
	/**
	 * Used to change the no. of seats after the screen object is created.
	 * @param noOfSeats
	 */
	public void setCapacity(int noOfSeats) {
		validateParams(name, noOfSeats, size);
		capacity = noOfSeats;
	}
	
	/**
	 * Used to change the screen name after the screen object is created.
	 * @param newName
	 */
	public void changeName(String newName) {
		validateParams(newName, capacity, size);
		name = newName;
	}
	
	/**
	 * Used to change the size of the screen after the screen object is created.
	 * @param screenSize
	 */
	public void setSize(int screenSize) {
		validateParams(name, screenSize, capacity);
		size = screenSize;
	}
	
	/**
	 * Change the status of the screen from active to inactive and vice versa
	 */
	public void changeStatus() {
		isActive = !isActive;
	}

}
