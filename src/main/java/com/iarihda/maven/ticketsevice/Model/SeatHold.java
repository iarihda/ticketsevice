package com.iarihda.maven.ticketsevice.Model;

/**
 * Transaction Entity to hold the seat
 * @author Adhirai Manickam
 */
public class SeatHold {
	
	private int id;
	private int noOfSeats;
	private long holdStartTime;
	private int customerId;
	private String[] heldSeats;
	
	/**
	 * Constructor
	 * @param holdId - unique id
	 * @param seatCount - no. of seats reserver
	 * @param customer - customer id
	 * @param seats - seats held
	 */
	SeatHold(int holdId, int seatCount, int customer, String[] seats) {
		id = holdId;
		noOfSeats = seatCount;
		holdStartTime = System.currentTimeMillis();
		customerId = customer;
		heldSeats = seats;
	}
	
	int getId() {
		return id;
	}
	
	int getSeatCount() {
		return noOfSeats;
	}
	
	int getCustomer() {
		return customerId;
	}
	
	String[] getHeldSeats() {
		return heldSeats;
	}
	
	boolean isActive() {
		return System.currentTimeMillis()<holdStartTime+120000;
	}
	
}
