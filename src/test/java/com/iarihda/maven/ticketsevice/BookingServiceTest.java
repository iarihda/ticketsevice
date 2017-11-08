import static org.junit.Assert.*;
import model.SeatHold;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import service.BookingService;
import setup.TheatreSetup;


/**
 * Test Booking Service
 * @author Adhirai Manickam
 */
public class BookingServiceTest {

	BookingService bkSrvc;
	int rows;
	int columns;
	int holdTime;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup(){
		TheatreSetup venue = new TheatreSetup();
		bkSrvc = new BookingService(venue);
		rows = venue.getRowCount();
		columns = venue.getColumnCount();
		holdTime = venue.getHoldLimit();
	}
	
	/*********************************************************************************************************************************
	 * BookingService() *
	 ***********************************/
	
	@Test
	public void TestConstructor_NullVenue_ShouldThrowException() {
		thrown.expect(NullPointerException.class);
		new BookingService(null);
	}
	
	/*********************************************************************************************************************************
	 * numSeatsAvailable() *
	 ***********************************/
	
	@Test
	public void TestNumSeatsAvailable_ValidScenario_ShouldPass() {
		assertEquals(rows*columns, bkSrvc.numSeatsAvailable());
	}
	
	/*********************************************************************************************************************************
	 * findAndHoldSeats() *
	 ***********************************/
	
	@Test
	public void TestFindAndHoldSeats_NullCustomerEmail_ShouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats(columns, null));
	}
	@Test
	public void TestFindAndHoldSeats_ZeroSeatCount_ShouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats(0, "test@gmail.com"));
	}
	
	@Test
	public void TestFindAndHoldSeats_SeatsNotAvailable_ShouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats((rows*columns)+1, "test@gmail.com"));
	}
	
	@Test
	public void TestFindAndHoldSeats_SeatsAvailableInSameRow_ShouldReturnSeatHoldObject(){
		SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
		assertEquals(columns,heldSeats.getHeldSeats().length);
	}
	
	@Test
	public void TestFindAndHoldSeats_SeatsAvailableInDifferentRows_ShouldReturnSeatHoldObject(){
		for(int i=0;i<rows;i++)
			bkSrvc.findAndHoldSeats(columns-1, "test@gmail.com");
		SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
		assertEquals(columns,heldSeats.getHeldSeats().length);
	}
	
	/*********************************************************************************************************************************
	 * reserveSeats() *
	 ***********************************/
	
	@Test
	public void TestReserveSeats_InValidSeatHoldId_ShouldReturnNull(){
		assertNull(bkSrvc.reserveSeats(-1, "test@gmail.com"));
	}
	
	@Test
	public void TestReserveSeats_NullCustomerEmail_ShouldReturnNull(){
		assertNull(bkSrvc.reserveSeats(columns, null));
	}
	
	@Test
	public void TestReserveSeats_ExpiredHold_ShouldReturnNull(){
		try {
			SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
			Thread.sleep(holdTime+1000);
			assertNull(bkSrvc.reserveSeats(heldSeats.getId(), "test@gmail.com"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void TestReserveSeats_ValidParams_ShouldReturnConfirmationCode(){
		assertNotNull(bkSrvc.reserveSeats(bkSrvc.findAndHoldSeats(columns, "test@gmail.com").getId(), "test@gmail.com"));
	}

}
