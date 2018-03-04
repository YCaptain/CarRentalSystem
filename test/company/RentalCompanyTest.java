package company;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import car.Car;
import car.CarFactory;
import car.RegistrationNumber;
import car.SmallCar;
import driver.DriverLicence;
import driver.LicenceNumber;
import driver.Name;

public class RentalCompanyTest {
	private static RentalCompany company;

	private static RegistrationNumber rn = new RegistrationNumber('a',1234);
	private static Car car = new SmallCar(rn);

	private static DriverLicence dl;
	private static Name name = Name.valueOf("Jack Chan");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static Calendar cl = new GregorianCalendar(1990, 6, 1);
	private static Date dateOfBirth = cl.getTime();
	private static LicenceNumber ln = LicenceNumber.valueOf("JC-1990-66");
	private static Date dateOfIssue;
	private static boolean isRented = false;

	@Test
	public void testGetInstance() {
		company = RentalCompany.getInstance();
		assertEquals(0, company.availableCars("small"));
		RentalCompany company1 = RentalCompany.getInstance();
		assertEquals(true, company1 == company);
		RentalCompany.clear();
	}

	@Test
	public void testIssueAndTerminate() {
		company = RentalCompany.getInstance();
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = DriverLicence.getInstance(name, dateOfBirth, ln, dateOfIssue, isRented);

		CarFactory.getInstance("small", rn);

		assertEquals(true, company.issueCar(dl, "small"));
		car.issue();
		Car car1 = company.getCar(dl);
		assertEquals(true, car.equals(car1));

		assertEquals(2, company.drive(car, 30));
		assertEquals(2, company.terminateRental(dl));
		RentalCompany.clear();
	}

	@Test
	public void testGetRentedCarsAndDrive() {
		company = RentalCompany.getInstance();
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = DriverLicence.getInstance(name, dateOfBirth, ln, dateOfIssue, isRented);
		LicenceNumber ln1 = LicenceNumber.valueOf("AB-1990-66");
		DriverLicence dl1 = DriverLicence.getInstance(name, dateOfBirth, ln1, dateOfIssue, isRented);
		RegistrationNumber rn1 = new RegistrationNumber('b',4567);
		CarFactory.getInstance("small", rn);
		CarFactory.getInstance("large", rn1);

		company.issueCar(dl, "small");
		Collection<Car> rented = company.getRentedCars();
		assertEquals(1, rented.size());
		assertEquals(0, company.availableCars("small"));
		assertEquals(1, company.availableCars("large"));
		company.issueCar(dl1, "large");
		assertEquals(false, company.issueCar(dl1, "large"));
		assertEquals(0, company.availableCars("large"));
		rented = company.getRentedCars();
		assertEquals(2, rented.size());

		car = company.getCar(dl1);
		assertEquals(3, company.drive(car, 30));
		assertEquals(7, company.drive(car, 66));
		assertEquals(10, company.terminateRental(dl1));
		assertEquals(-1, company.terminateRental(dl1));
		RentalCompany.clear();
	}



}
