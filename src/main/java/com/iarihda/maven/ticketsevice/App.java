package com.iarihda.maven.ticketsevice;

import com.iarihda.maven.ticketsevice.model.SeatHold;
import com.iarihda.maven.ticketsevice.service.BookingService;

public class App {

	public static void main(String[] args) {
		TheatreSetup venue = new TheatreSetup();
		BookingService bs = new BookingService(venue);
		SeatHold s1 = bs.findAndHoldSeats(4, "email");
		for(String s : s1.getHeldSeats())
			System.out.print(s+" ");
		System.out.println();
		SeatHold s2 = bs.findAndHoldSeats(5, "email");
		for(String s : s2.getHeldSeats())
			System.out.print(s+" ");
		System.out.println();
		SeatHold s3 = bs.findAndHoldSeats(2, "email");
		for(String s : s3.getHeldSeats())
			System.out.print(s+" ");
		System.out.println();
		SeatHold s4 = bs.findAndHoldSeats(4, "email");
		for(String s : s4.getHeldSeats())
			System.out.print(s+" ");
		System.out.println();
		SeatHold s5 = bs.findAndHoldSeats(6, "email");
		for(String s : s5.getHeldSeats())
			System.out.print(s+" ");
	}

}
