package car;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import company.RentalCompany;

public class RegistrationNumberTest {
	private static RegistrationNumber rn;

	@Test
	public void testConstructorAndSetter() {
		rn = new RegistrationNumber('a',1234);
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetLetterException() {
		rn = new RegistrationNumber(' ',1234);
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNumException() {
		rn = new RegistrationNumber('a',123);
		RegistrationNumber rn1 = new RegistrationNumber('a',12354);
		RentalCompany.clear();
	}

	@Test
	public void testEquals() {
		rn = new RegistrationNumber('a',1234);
		RegistrationNumber rn1 = rn;
		assertEquals(true, rn.equals(rn1));
		rn1 = new RegistrationNumber('a',1234);
		assertEquals(true, rn.equals(rn1));
		rn1 = new RegistrationNumber('a',1233);
		assertEquals(false, rn.equals(rn1));
		rn1 = new RegistrationNumber('b',1234);
		assertEquals(false, rn.equals(rn1));
		assertEquals(false, rn.equals("123"));
		RentalCompany.clear();
	}

	@Test
	public void testToString() {
		rn = new RegistrationNumber('a',1234);
		assertEquals("a1234", rn.toString());
		RentalCompany.clear();
	}

	@Test
	public void testValueOf() {
		rn = RegistrationNumber.valueOf("a1234");
		assertEquals('a', rn.getLetter());
		assertEquals(1234, rn.getNum());
		RentalCompany.clear();
	}
}
