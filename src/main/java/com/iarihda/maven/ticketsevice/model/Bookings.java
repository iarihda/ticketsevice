package com.iarihda.maven.ticketsevice.model;

import java.util.concurrent.atomic.AtomicInteger;

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
	
	public SeatHold getSeats(){
		return seats;
	}
	
	public String getCustomer(){
		return customerEmail;
	}
	
	public String getConfirmationCode(){
		return confirmationCode;
	}
}
