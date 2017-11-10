package com.iarihda.maven.ticketsevice;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test TheatreSetup
 * @author Adhirai Manickam
 */
public class TheatreSetupTest {

	TheatreSetup venue;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/*********************************************************************************************************************************
	 * TheatreSetup() *
	 ***********************************/
	
	@Test
	public void testTheatreSetup() {
		assertNotNull(new TheatreSetup());
	}
	
	/*********************************************************************************************************************************
	 * getRowCount() *
	 ***********************************/
	
	@Test
	public void testGetRowCount_validScenario_shouldPass() {
		assertNotNull(TheatreSetup.getRowCount());
	}
	
	/*********************************************************************************************************************************
	 * getColumnCount() *
	 ***********************************/
	
	@Test
	public void testGetColumnCount_validScenario_shouldPass() {
		assertNotNull(TheatreSetup.getColumnCount());
	}

	/*********************************************************************************************************************************
	 * getHoldLimit() *
	 ***********************************/
	
	@Test
	public void testGetHoldLimit_validScenario_shouldPass() {
		assertNotNull(TheatreSetup.getHoldLimit());
	}
	
	/*********************************************************************************************************************************
	 * getScreen() *
	 ***********************************/

	@Test
	public void testGetScreen_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getScreen();
	}
	
	@Test
	public void testGetScreen_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getScreen());
	}

	/*********************************************************************************************************************************
	 * getMovie() *
	 ***********************************/
	
	@Test
	public void testGetMovie_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getMovie();
	}
	
	@Test
	public void testGetMovie_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getMovie());
	}
	
	/*********************************************************************************************************************************
	 * getShow() *
	 ***********************************/

	@Test
	public void testGetShow_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getShow();
	}
	
	@Test
	public void testGetShow_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getShow());
	}

}
