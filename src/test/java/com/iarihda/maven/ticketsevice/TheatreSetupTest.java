package com.iarihda.maven.ticketsevice;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TheatreSetupTest {

	TheatreSetup venue;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testTheatreSetup() {
		assertNotNull(new TheatreSetup());
	}

	@Test
	public void testGetRowCount_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getRowCount();
	}
	
	@Test
	public void testGetRowCount_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getRowCount());
	}

	@Test
	public void testGetColumnCount_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getColumnCount();
	}
	
	@Test
	public void testGetColumnCount_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getColumnCount());
	}

	@Test
	public void testGetHoldLimit_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		venue.getHoldLimit();
	}
	
	@Test
	public void testGetHoldLimit_objectCreated_shouldPass() {
		venue = new TheatreSetup();
		assertNotNull(venue.getHoldLimit());
	}

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
