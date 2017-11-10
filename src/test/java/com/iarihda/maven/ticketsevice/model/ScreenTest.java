package com.iarihda.maven.ticketsevice.model;

/**
 * Test Screen
 * @author Adhirai Manickam
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.iarihda.maven.ticketsevice.model.Screen;

public class ScreenTest {

	Screen scrn;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/*********************************************************************************************************************************
	 * Screen() *
	 ***********************************/
	
	@Test
	public final void testScreen_invalidParams_shouldThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No. of seats and screen size should be a positive value");
		new Screen("ScrnOne",0, 0, true);
	}
	
	@Test
	public final void testScreen_validParams_shouldPass() {
		assertNotNull(new Screen("ScrnOne",100, 50, true));
	}

	/*********************************************************************************************************************************
	 * getId() *
	 ***********************************/
	
	@Test
	public final void testGetId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getId();
	}
	
	@Test
	public final void testGetId_objectCreated_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true); 
		assertNotNull(scrn.getId());
	}

	/*********************************************************************************************************************************
	 * getCapacity() *
	 ***********************************/
	@Test
	public final void testGetCapacity_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getCapacity();
	}
	
	@Test
	public final void testGetCapacity_objectCreated_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true); 
		assertEquals(25, scrn.getCapacity());
	}

	/*********************************************************************************************************************************
	 * getSize() *
	 ***********************************/
	@Test
	public final void testGetSize_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getSize();
	}
	
	@Test
	public final void testGetSize_objectCreated_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true); 
		assertEquals(50, scrn.getSize());
	}
	
	/*********************************************************************************************************************************
	 * getName() *
	 ***********************************/
	@Test
	public final void testGetName_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getName();
	}
	
	@Test
	public final void testGetName_objectCreated_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true); 
		assertEquals("ScrnOne", scrn.getName());
	}

	/*********************************************************************************************************************************
	 * isActive() *
	 ***********************************/
	@Test
	public final void testIsActive_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.isActive();
	}
	
	@Test
	public final void testIsActive_objectCreated_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true); 
		assertTrue(scrn.isActive());
	}

	/*********************************************************************************************************************************
	 * setCapacity() *
	 ***********************************/
	@Test
	public final void testSetCapacity_invalidParams_shouldThrowException() {
		scrn = new Screen("ScrnOne",25, 50, true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No. of seats and screen size should be a positive value");
		scrn.setCapacity(-1);
	}
	
	@Test
	public final void testSetCapacity_validParams_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true);
		scrn.setCapacity(150);
		assertEquals(150, scrn.getCapacity());
	}

	/*********************************************************************************************************************************
	 * setSize() *
	 ***********************************/
	
	@Test
	public final void testSetSize_invalidParams_shouldThrowException() {
		scrn = new Screen("ScrnOne",25, 50, true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No. of seats and screen size should be a positive value");
		scrn.setSize(0);
	}
	
	@Test
	public final void testSetSize_validParams_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true);
		scrn.setSize(100);
		assertEquals(100, scrn.getSize());
	}
	
	/*********************************************************************************************************************************
	 * changeName() *
	 ***********************************/
	
	@Test
	public final void testChangeName_invalidParams_shouldThrowException() {
		scrn = new Screen("ScrnOne",25, 50, true);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Screen name cannot be null");
		scrn.changeName(null);
	}
	
	@Test
	public final void testChangeName_validParams_shouldPass() {
		scrn = new Screen("ScrnOne",25, 50, true);
		scrn.changeName("S_One");
		assertEquals("S_One", scrn.getName());
	}

	/*********************************************************************************************************************************
	 * changeStatus() *
	 ***********************************/
	@Test
	public final void testChangeStatus() {
		scrn = new Screen("ScrnOne",25, 50, true);
		boolean before_change = scrn.isActive();
		scrn.changeStatus();
		boolean after_change = scrn.isActive();
		assertTrue(before_change==(!after_change));
	}

}
