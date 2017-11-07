package setup;

import model.SeatHold;
import service.BookingService;

class TicketThread extends Thread{
	
	private BookingService bookingService;
	private long waitTime;
	private int numSeats;
	private String customerEmail;
	
	public TicketThread(BookingService bs, long ms, int seatNo, String email) {
		bookingService = bs;
		waitTime = ms;
		numSeats = seatNo;
		customerEmail = email;
	}
	
	public void run(){
		try {
			System.out.println("Starting booking for "+customerEmail);
			SeatHold sh = bookingService.findAndHoldSeats(numSeats, customerEmail);
			if(sh==null){
				throw new InterruptedException("Requested number of seats ("+numSeats+") not available for "+customerEmail+". Please try with lesser quantity.");
			}
			Thread.sleep(waitTime);
			String confirmation = bookingService.reserveSeats(sh.getId(), customerEmail);
			if(confirmation == null){
				System.out.println("Hold expired due to time out for "+customerEmail+". Please try again.");
			} else {
				System.out.println("Tickets booked for "+customerEmail+". Confirmation Code - "+confirmation);
				for(String s : sh.getHeldSeats())
					System.out.print(s+" ");
				System.out.println();
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

public class App {

	public static void main(String[] args) {
		TheatreSetup venue = new TheatreSetup();
		BookingService bookingService = new BookingService(venue);
		try {
			TicketThread t1 = new TicketThread(bookingService, 15000, 4, "a@gmail.com");
			t1.start();
			TicketThread t2 = new TicketThread(bookingService, 0, 2, "b@gmail.com");
			t2.start();
			TicketThread t3 = new TicketThread(bookingService, 30000, 5, "c@gmail.com");
			t3.start();
			Thread.sleep(5000);
			TicketThread t4 = new TicketThread(bookingService, 5000, 18, "d@gmail.com");
			t4.start();
			Thread.sleep(5000);
			TicketThread t5 = new TicketThread(bookingService, 5000, 13, "d@gmail.com");
			t5.start();
			Thread.sleep(30000);
			TicketThread t6 = new TicketThread(bookingService, 0, 5, "c@gmail.com");
			t6.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}


}
