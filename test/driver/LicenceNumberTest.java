package driver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LicenceNumberTest {
	private static LicenceNumber ln;

	@Test
	public void testConstructorAndSetter() {
		ln = new LicenceNumber("JK", 1990, 66);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFirstException() {
		ln = new LicenceNumber("", 1990, 66);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSecondException() {
		ln = new LicenceNumber("JK", 0, 66);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetThirdException() {
		ln = new LicenceNumber("JK", 1990, 0);
	}

	@Test
	public void testEquals() {
		ln = new LicenceNumber("JK", 1990, 66);
		LicenceNumber ln1 = ln;
		assertEquals(true, ln.equals(ln1));
		ln1 = new LicenceNumber("JK", 1990, 66);
		assertEquals(true, ln.equals(ln1));
		assertEquals(false, ln.equals("123"));
	}

	@Test
	public void testValueOf() {
		ln = LicenceNumber.valueOf("JK-1990-66");
		assertEquals("JK", ln.getFirst());
		assertEquals(1990, ln.getSecond());
		assertEquals(66, ln.getThird());
	}

}
