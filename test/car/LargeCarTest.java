package car;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import company.RentalCompany;

public class LargeCarTest {
	private static Car car;
	private static RegistrationNumber rn = new RegistrationNumber('a',1234);

	@Test
	public void testDrive() {
		car = new LargeCar(rn);
		assertEquals(-1, car.drive(66));
		RentalCompany.clear();
	}

}
