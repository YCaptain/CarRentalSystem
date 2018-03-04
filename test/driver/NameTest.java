package driver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NameTest {
	private static Name name;

	@Test
	public void testConstructorAndSetter() {
		name = new Name("Jack", "Chan");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetFirstException() {
		name = new Name("", "Chan");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLastException() {
		name = new Name("Jack", "");
	}

	@Test
	public void testEquals() {
		name = new Name("Jack", "Chan");
		Name n = name;
		assertEquals(true, name.equals(n));
		n = new Name("Jack", "Chan");
		assertEquals(true, name.equals(n));
		n = new Name("Ja", "Chan");
		assertEquals(false, name.equals(n));
		n = new Name("Jack", "Ch");
		assertEquals(false, name.equals(n));
	}

	@Test
	public void testHash() {
		name = new Name("Jack", "Chan");
		int hc = 17;
		hc = 37 * hc + "Jack".hashCode();
		hc = 37 * hc + "Chan".hashCode();
		assertEquals(hc, name.hashCode());
	}

	@Test
	public void testToString() {
		name = new Name("Jack", "Chan");
		assertEquals("Jack Chan", name.toString());
	}

	@Test
	public void testValueOf() {
		name = Name.valueOf("Jack Chan");
		assertEquals("Jack", name.getFirstName());
		assertEquals("Chan", name.getLastName());
	}

}
