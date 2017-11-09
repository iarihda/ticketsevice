package com.iarihda.maven.ticketsevice.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		thrown.expectMessage("Invalid movie id");
		new Show(0, 0, null, true);
	}
	
	@Test
	public final void testMovie_validParams_shouldPass() {
		assertNotNull(new Show(mv.getId(), scrn.getId(), "6PM", true));
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
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		assertNotNull(shw.getId());
	}
	
	/*********************************************************************************************************************************
	 * getMovieId() *
	 ***********************************/
	
	@Test
	public final void testGetMovieId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getMovieId();
	}
	
	@Test
	public final void testGetMovieId_objectCreated_shouldPass() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		assertEquals(mv.getId(),shw.getMovieId());
	}

	/*********************************************************************************************************************************
	 * getScreenId() *
	 ***********************************/
	
	@Test
	public final void testGetScreenId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		shw.getScreenId();
	}
	
	@Test
	public final void testGetScreenId_objectCreated_shouldPass() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		assertEquals(scrn.getId(),shw.getScreenId());
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
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
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
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		assertTrue(shw.isActive());
	}
	
	/*********************************************************************************************************************************
	 * setMovieId() *
	 ***********************************/
	
	@Test
	public final void testSetMovieId_invalidParams_shouldThrowException() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Invalid movie id");
		shw.setMovieId(0);
	}
	
	@Test
	public final void testSetMovieId_validParams_shouldPass() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		mv = new Movie("Avengers II", "English", true); 
		shw.setMovieId(mv.getId());
		assertEquals(mv.getId(), shw.getMovieId());
	}
	
	/*********************************************************************************************************************************
	 * setScreenId() *
	 ***********************************/
	
	@Test
	public final void testSetScreenId_invalidParams_shouldThrowException() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Invalid screen id");
		shw.setScreenId(0);
	}
	
	@Test
	public final void testSetScreenId_validParams_shouldPass() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		scrn = new Screen("S_One",100, 50, true);
		shw.setScreenId(scrn.getId());
		assertEquals(scrn.getId(), shw.getScreenId());
	}
	
	/*********************************************************************************************************************************
	 * setTime() *
	 ***********************************/
	
	@Test
	public final void testSetTime_invalidParams_shouldThrowException() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Show time cannot be null");
		shw.setTime(null);
	}
	
	@Test
	public final void testSetTime_validParams_shouldPass() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		shw.setTime("7PM");
		assertEquals("7PM", shw.getTime());
	}
	
	/*********************************************************************************************************************************
	 * changeStatus() *
	 ***********************************/
	@Test
	public final void testChangeStatus() {
		shw = new Show(mv.getId(), scrn.getId(), "6PM", true);
		boolean before_change = shw.isActive();
		shw.changeStatus();
		boolean after_change = shw.isActive();
		assertTrue(before_change==(!after_change));
	}
}
