package com.iarihda.maven.ticketsevice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A domain class which represents a booking for a show. It has the following fields
 * SeatHold object – The specific seats which are reserved for the booking and related info
 * Customer Email – Unique Identifier for a customer
 * Confirmation Code – A unique string which confirms and identifies the booking
 * @author Adhirai Manickam
 */
public class Bookings {
	
	SeatHold seats;
	String customerEmail;
	String confirmationCode;
	
	private static AtomicInteger idGenerator = new AtomicInteger(1000);
	
	/**
	 * Constructor
	 * @param seatsReserved
	 * @param customer
	 * @param movieName
	 */
	public Bookings(SeatHold seatsReserved, String customer, int showId) {
		seats = seatsReserved;
		customerEmail = customer;
		confirmationCode = String.valueOf(showId)+String.valueOf(idGenerator.getAndIncrement());
	}
	
	/*
	 * @return seathold object
	 */
	public SeatHold getSeats(){
		return seats;
	}
	
	/*
	 * @return customer email
	 */
	public String getCustomer(){
		return customerEmail;
	}
	
	/*
	 * @return confirmationCode
	 */
	public String getConfirmationCode(){
		return confirmationCode;
	}
}
