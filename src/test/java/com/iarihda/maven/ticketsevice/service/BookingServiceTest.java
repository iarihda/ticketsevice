package com.iarihda.maven.ticketsevice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.iarihda.maven.ticketsevice.TheatreSetup;
import com.iarihda.maven.ticketsevice.model.SeatHold;
import com.iarihda.maven.ticketsevice.service.BookingService;

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
	public void testConstructor_nullVenue_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		new BookingService(null);
	}
	
	/*********************************************************************************************************************************
	 * numSeatsAvailable() *
	 ***********************************/
	
	@Test
	public void testNumSeatsAvailable_validScenario_shouldPass() {
		assertEquals(rows*columns, bkSrvc.numSeatsAvailable());
	}
	
	@Test
	public void testNumSeatsAvailable_expiredHold_shouldPass() {
		try {
			bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
			Thread.sleep(holdTime+1000);
			int count_after_expiration = bkSrvc.numSeatsAvailable();
			assertEquals(rows*columns, count_after_expiration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*********************************************************************************************************************************
	 * findAndHoldSeats() *
	 ***********************************/
	
	@Test
	public void testFindAndHoldSeats_nullCustomerEmail_shouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats(columns, null));
	}
	@Test
	public void testFindAndHoldSeats_zeroSeatCount_shouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats(0, "test@gmail.com"));
	}
	
	@Test
	public void testFindAndHoldSeats_seatsNotAvailable_shouldReturnNull(){
		assertNull(bkSrvc.findAndHoldSeats((rows*columns)+1, "test@gmail.com"));
	}
	
	@Test
	public void testFindAndHoldSeats_seatsAvailableInSameRow_shouldReturnSeatHoldObject(){
		SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
		assertEquals(columns,heldSeats.getHeldSeats().length);
	}
	
	@Test
	public void testFindAndHoldSeats_seatsAvailableInDifferentRows_shouldReturnSeatHoldObject(){
		for(int i=0;i<rows;i++)
			bkSrvc.findAndHoldSeats(columns-1, "test@gmail.com");
		SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
		assertEquals(columns,heldSeats.getHeldSeats().length);
	}
	
	/*********************************************************************************************************************************
	 * reserveSeats() *
	 ***********************************/
	
	@Test
	public void testReserveSeats_invalidSeatHoldId_shouldReturnNull(){
		assertNull(bkSrvc.reserveSeats(-1, "test@gmail.com"));
	}
	
	@Test
	public void testReserveSeats_nullCustomerEmail_shouldReturnNull(){
		assertNull(bkSrvc.reserveSeats(columns, null));
	}
	
	@Test
	public void testReserveSeats_expiredHold_shouldReturnNull(){
		try {
			SeatHold heldSeats = bkSrvc.findAndHoldSeats(columns, "test@gmail.com");
			Thread.sleep(holdTime+1000);
			assertNull(bkSrvc.reserveSeats(heldSeats.getId(), "test@gmail.com"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void testReserveSeats_validParams_shouldPass(){
		assertNotNull(bkSrvc.reserveSeats(bkSrvc.findAndHoldSeats(columns, "test@gmail.com").getId(), "test@gmail.com"));
	}

}
