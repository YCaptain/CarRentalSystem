package car;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import company.RentalCompany;

public class CarFactoryTest {
	private static Car car;
	private static RegistrationNumber rn = new RegistrationNumber('a',1234);
	private static int capacity = 49;

	@Test
	public void testGetInstance() {
		car = CarFactory.getInstance("small", rn);
		assertEquals(rn.toString(), car.regisNum().toString());
		assertEquals(capacity, car.capacity());
		assertEquals(capacity, car.fuel());
		assertEquals(true, car.isFull());
		Car car1 = CarFactory.getInstance("small", rn);
		rn = new RegistrationNumber('b',3456);
		car1 = CarFactory.getInstance("large", rn);
		RentalCompany.clear();
	}

	@Test
	public void testIssueAndTerminate() {
		car = CarFactory.getInstance("small", rn);
		assertEquals(true, car.issue());
		assertEquals(true, car.isRented());
		assertEquals(true, CarFactory.getInstance("small", rn).issue());
		assertEquals(false, CarFactory.getInstance("small", rn).isRented());
		assertEquals(true, car.terminateRental());
		assertEquals(false, car.isRented());
		assertEquals(true, CarFactory.terminateRental(car));
		assertEquals(false, CarFactory.getInstance("small", rn).isRented());
		assertEquals(true, CarFactory.issue(car));
		assertEquals(true, CarFactory.getInstance("small", rn).isRented());
		RentalCompany.clear();
	}

}
