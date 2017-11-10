package com.iarihda.maven.ticketsevice.model;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test Movie
 * @author Adhirai Manickam
 */
public class MovieTest {

	Movie mv;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/*********************************************************************************************************************************
	 * Movie() *
	 ***********************************/
	@Test
	public void testMovie_invalidParams_shouldThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Movie name or language cannot be null");
		new Movie(null, null, true);
	}
	
	@Test
	public final void testMovie_validParams_shouldPass() {
		assertNotNull(new Movie("Avengers", "English", true));
	}
	
	/*********************************************************************************************************************************
	 * getId() *
	 ***********************************/
	
	@Test
	public final void testGetId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		mv.getId();
	}
	
	@Test
	public final void testGetId_objectCreated_shouldPass() {
		mv = new Movie("Avengers", "English", true);
		assertNotNull(mv.getId());
	}
	
	
	/*********************************************************************************************************************************
	 * getTitle() *
	 ***********************************/
	
	@Test
	public final void testGetTitle_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		mv.getTitle();
	}
	
	@Test
	public final void testGetTitle_objectCreated_shouldPass() {
		mv = new Movie("Avengers", "English", true);
		assertEquals("Avengers",mv.getTitle());
	}
	
	/*********************************************************************************************************************************
	 * getLanguage() *
	 ***********************************/
	
	@Test
	public final void testGetLanguage_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		mv.getLanguage();
	}
	
	@Test
	public final void testGetLanguage_objectCreated_shouldPass() {
		mv = new Movie("Avengers", "English", true);
		assertEquals("English",mv.getLanguage());
	}
	
	/*********************************************************************************************************************************
	 * isActive() *
	 ***********************************/
	@Test
	public final void testIsActive_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		mv.isActive();
	}
	
	@Test
	public final void testIsActive_objectCreated_shouldPass() {
		mv = new Movie("Avengers", "English", true); 
		assertTrue(mv.isActive());
	}
	
	/*********************************************************************************************************************************
	 * setTitle() *
	 ***********************************/
	
	@Test
	public final void testSetTitle_invalidParams_shouldThrowException() {
		mv = new Movie("Avengers", "English", true); 
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Movie name or language cannot be null");
		mv.setTitle(null);
	}
	
	@Test
	public final void testSetTitle_validParams_shouldPass() {
		mv = new Movie("Avengers", "English", true); 
		mv.setTitle("Avengers II");
		assertEquals("Avengers II", mv.getTitle());
	}
	
	/*********************************************************************************************************************************
	 * setLanguage() *
	 ***********************************/
	
	@Test
	public final void testSetLanguage_invalidParams_shouldThrowException() {
		mv = new Movie("Avengers", "English", true); 
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Movie name or language cannot be null");
		mv.setLanguage(null);
	}
	
	@Test
	public final void testSetLanguage_validParams_shouldPass() {
		mv = new Movie("Avengers", "English", true); 
		mv.setLanguage("Spanish");
		assertEquals("Spanish", mv.getLanguage());
	}
	
	/*********************************************************************************************************************************
	 * changeStatus() *
	 ***********************************/
	@Test
	public final void testChangeStatus() {
		mv = new Movie("Avengers", "English", true);
		boolean before_change = mv.isActive();
		mv.changeStatus();
		boolean after_change = mv.isActive();
		assertTrue(before_change==(!after_change));
	}

}
