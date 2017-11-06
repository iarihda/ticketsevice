import model.SeatHold;
import service.BookingService;


public class App {

	public static void main(String[] args) {
		BookingService bs = new BookingService();
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
