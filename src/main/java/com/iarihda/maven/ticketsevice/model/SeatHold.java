package com.iarihda.maven.ticketsevice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Transaction Entity to hold the seat
 * @author Adhirai Manickam
 */
public class SeatHold {
	
	private int id;
	private int noOfSeats;
	private long holdStartTime;
	private String customerEmail;
	private String[] heldSeats;
	
	private static AtomicInteger idGenerator = new AtomicInteger(1000);
	
	/**
	 * Constructor
	 * @param seatCount - no. of seats reserver
	 * @param customer - customer id
	 * @param seats - seats held
	 */
	public SeatHold(int seatCount, String customer, String[] seats) {
		id = idGenerator.getAndIncrement();
		noOfSeats = seatCount;
		holdStartTime = System.currentTimeMillis();
		customerEmail = customer;
		heldSeats = seats;
	}
	
	public int getId() {
		return id;
	}
	
	int getSeatCount() {
		return noOfSeats;
	}
	
	String getCustomer() {
		return customerEmail;
	}
	
	public String[] getHeldSeats() {
		return heldSeats;
	}
	
	boolean isActive() {
		return System.currentTimeMillis()<holdStartTime+120000;
	}
	
}