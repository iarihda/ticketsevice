import static org.junit.Assert.*;
import model.Screen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import setup.TheatreSetup;


/**
 * @author c39788
 *
 */
public class ScreenTest {
	
	Screen scrn;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test method for Screen
	 */
	
	@Test
	public final void testScreen_invalidParams_shouldThrowException() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("No. of seats and screen size should be a positive value");
		new Screen(0, 0, true);
	}
	
	@Test
	public final void testScreen_capacityRangeExceeded_shouldPass() {
		assertEquals(625, new Screen(1000, 50, true).getCapacity());
	}
	
	@Test
	public final void testScreen_validParams_shouldPass() {
		assertNotNull(new Screen(100, 50, true));
	}

	/**
	 * Test method for {@link model.Screen#getId()}.
	 */
	@Test
	public final void testGetId_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getId();
	}
	
	@Test
	public final void testGetId_objectCreated_shouldPass() {
		scrn = new Screen(25, 50, true); 
		assertEquals(1000, scrn.getId());
	}

	/**
	 * Test method for {@link model.Screen#getCapacity()}.
	 */
	@Test
	public final void testGetCapacity_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getCapacity();
	}
	
	@Test
	public final void testGetCapacity_objectCreated_shouldPass() {
		scrn = new Screen(25, 50, true); 
		assertEquals(25, scrn.getCapacity());
	}

	/**
	 * Test method for {@link model.Screen#getSize()}.
	 */
	@Test
	public final void testGetSize_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.getSize();
	}
	
	@Test
	public final void testGetSize_objectCreated_shouldPass() {
		scrn = new Screen(25, 50, true); 
		assertEquals(50, scrn.getSize());
	}

	/**
	 * Test method for {@link model.Screen#isActive()}.
	 */
	@Test
	public final void testIsActive_objectNotCreated_shouldThrowException() {
		thrown.expect(NullPointerException.class);
		scrn.isActive();
	}
	
	@Test
	public final void testIsActive_objectCreated_shouldPass() {
		scrn = new Screen(25, 50, true); 
		assertTrue(scrn.isActive());
	}

	/**
	 * Test method for {@link model.Screen#setCapacity(int)}.
	 */
	@Test
	public final void testSetCapacity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Screen#setSize(int)}.
	 */
	@Test
	public final void testSetSize() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link model.Screen#changeStatus()}.
	 */
	@Test
	public final void testChangeStatus() {
		fail("Not yet implemented"); // TODO
	}

}
