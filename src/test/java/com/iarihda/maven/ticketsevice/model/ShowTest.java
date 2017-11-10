package com.iarihda.maven.ticketsevice.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test Show
 * @author Adhirai Manickam
 */
public class ShowTest {

	Show shw;
	Movie mv;
	Screen scrn;
	
	@Before
	public void setup() {
		mv = new Movie("Avengers", "English", true);
		scrn = new Screen("ScrnOne",100, 50, true);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*********************************************************************************************************************************
	 * Show() *
	 ***********************************/
	
	@Test
	public void testShow_invalidParams_shouldThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Both movie and screen are required to create show.");
		new Show(null, null, null, true);
	}
	
	@Test
	public final void testMovie_validParams_shouldPass() {
		assertNotNull(new Show(mv, scrn, "6PM", true));
	}
	
	/*********************************************************************************************************************************
	 * getId() *
	 ***********************************/
	
	@Test
	public final void testGetId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getId();
	}
	
	@Test
	public final void testGetId_objectCreated_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		assertNotNull(shw.getId());
	}
	
	/*********************************************************************************************************************************
	 * getMovie() *
	 ***********************************/
	
	@Test
	public final void testGetMovie_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getMovie();
	}
	
	@Test
	public final void testGetMovie_objectCreated_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		assertEquals(mv,shw.getMovie());
	}

	/*********************************************************************************************************************************
	 * getScreen() *
	 ***********************************/
	
	@Test
	public final void testGetScreen_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getScreen();
	}
	
	@Test
	public final void testGetScreen_objectCreated_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		assertEquals(scrn,shw.getScreen());
	}
	
	/*********************************************************************************************************************************
	 * getTime() *
	 ***********************************/
	
	@Test
	public final void testGetTime_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getTime();
	}
	
	@Test
	public final void testGetTime_objectCreated_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		assertEquals("6PM",shw.getTime());
	}
	
	/*********************************************************************************************************************************
	 * isActive() *
	 ***********************************/
	@Test
	public final void testIsActive_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.isActive();
	}
	
	@Test
	public final void testIsActive_objectCreated_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		assertTrue(shw.isActive());
	}
	
	/*********************************************************************************************************************************
	 * setMovie() *
	 ***********************************/
	
	@Test
	public final void testSetMovie_invalidParams_shouldThrowException() {
		shw = new Show(mv, scrn, "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Movie is not active. Cannot create show.");
		mv.changeStatus();
		shw.setMovie(mv);
		mv.changeStatus();
	}
	
	@Test
	public final void testSetMovie_validParams_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		mv = new Movie("Avengers II", "English", true); 
		shw.setMovie(mv);
		assertEquals(mv, shw.getMovie());
	}
	
	/*********************************************************************************************************************************
	 * setScreen() *
	 ***********************************/
	
	@Test
	public final void testSetScreen_invalidParams_shouldThrowException() {
		shw = new Show(mv, scrn, "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Screen is not active. Cannot create show.");
		scrn.changeStatus();
		shw.setScreen(scrn);
		scrn.changeStatus();
	}
	
	@Test
	public final void testSetScreen_validParams_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		scrn = new Screen("S_One",100, 50, true);
		shw.setScreen(scrn);
		assertEquals(scrn, shw.getScreen());
	}
	
	/*********************************************************************************************************************************
	 * setTime() *
	 ***********************************/
	
	@Test
	public final void testSetTime_invalidParams_shouldThrowException() {
		shw = new Show(mv, scrn, "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Show time cannot be null");
		shw.setTime(null);
	}
	
	@Test
	public final void testSetTime_validParams_shouldPass() {
		shw = new Show(mv, scrn, "6PM", true);
		shw.setTime("7PM");
		assertEquals("7PM", shw.getTime());
	}
	
	/*********************************************************************************************************************************
	 * changeStatus() *
	 ***********************************/
	@Test
	public final void testChangeStatus() {
		shw = new Show(mv, scrn, "6PM", true);
		boolean before_change = shw.isActive();
		shw.changeStatus();
		boolean after_change = shw.isActive();
		assertTrue(before_change==(!after_change));
	}
}
