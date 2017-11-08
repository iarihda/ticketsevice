package com.iarihda.maven.ticketsevice;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.iarihda.maven.ticketsevice.model.SeatHold;
import com.iarihda.maven.ticketsevice.service.BookingService;

/**
 * Ticket booking thread to hold and reserve the seats
 * @author Adhirai Manickam
 */
class TicketThread extends Thread{
	
	private static Logger log = LogManager.getLogger(Theatre.class);
	
	private BookingService bookingService;
	private long waitTime;
	private int numSeats;
	private String customerEmail;
	
	/**
	 * Constructor
	 * @param bs - Booking service object
	 * @param ms - Time (in milliseconds) the customer waits after holding the ticket and before reserving it 
	 * @param seatNo - No. of seats required by the customer
	 * @param email - Customer email
	 */
	public TicketThread(BookingService bs, long ms, int seatNo, String email) {
		bookingService = bs;
		waitTime = ms;
		numSeats = seatNo;
		customerEmail = email;
	}
	
	/**
	 * Thread starts running.
	 * Throws InterruptedException when there is not enough seats left to complete the purchase.
	 * Prints the confirmation number along with the seats if the purchase was successful.
	 */
	public void run(){
		try {
			SeatHold sh = bookingService.findAndHoldSeats(numSeats, customerEmail);
			if(sh==null){ 
				throw new InterruptedException("Requested number of seats ("+numSeats+") not available for "+customerEmail+". Please try with lesser quantity.");
			}
			log.info("Seats "+Arrays.toString(sh.getHeldSeats())+" are held for "+customerEmail);
			Thread.sleep(waitTime); //The customer takes waitTime milliseconds before completing the purchase
			String confirmation = bookingService.reserveSeats(sh.getId(), customerEmail);
			if(confirmation == null){
				log.info("Hold expired due to time out for "+customerEmail+". Please try again.");
			} else {
				log.info("Tickets booked for "+customerEmail+". Confirmation Code - "+confirmation);
				log.info("Seat Numbers for "+confirmation+" : "+Arrays.toString(sh.getHeldSeats()));
			}
		} catch (InterruptedException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


/**
 * Main class
 * @author Adhirai Manickam
 */
public class Theatre {

	public static void main(String[] args) {
		
		TheatreSetup venue = new TheatreSetup(); //Setup the screen, movie and show
		BookingService bookingService = new BookingService(venue); //Create a booking service for the show
		
		try {
			
			/* Three customers try to book tickets simultaneously */
			
			//A wants to hold 4 seats, wait for 15 seconds after and then reserve the seats
			TicketThread t1 = new TicketThread(bookingService, 15000, 4, "a@gmail.com"); 
			t1.start();
			
			//B wants to hold 2 seats and immediately reserve the seats without waiting
			TicketThread t2 = new TicketThread(bookingService, 0, 2, "b@gmail.com");
			t2.start();
			
			//C wants to hold 5 seats, wait for 30 seconds after and then reserve the seats
			TicketThread t3 = new TicketThread(bookingService, 30000, 5, "c@gmail.com");
			t3.start();
			
			/*After 5 seconds, another customer tries to book tickets*/
			
			Thread.sleep(5000);
			
			//D wants to hold 18 seats, wait for 5 seconds after and then reserve the seats
			TicketThread t4 = new TicketThread(bookingService, 5000, 18, "d@gmail.com");
			t4.start();
			
			/*After 5 seconds, D tries to book tickets again*/
			
			Thread.sleep(5000);
			
			//D wants to hold 13 seats, wait for 5 seconds after and then reserve the seats
			TicketThread t5 = new TicketThread(bookingService, 5000, 13, "d@gmail.com");
			t5.start();
			
			/*After 30 seconds, C tries to book tickets again*/
			
			Thread.sleep(30000);
			
			//C wants to hold 5 seats and immediately reserve the seats without waiting
			TicketThread t6 = new TicketThread(bookingService, 0, 5, "c@gmail.com");
			t6.start();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
