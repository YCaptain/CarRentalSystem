package driver;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import company.RentalCompany;

public class DriverLicenceTest {
	private static DriverLicence dl;
	private static Name name = Name.valueOf("Jack Chan");
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private static Calendar cl = new GregorianCalendar(1990, 6, 1);
	private static Date dateOfBirth = cl.getTime();
	private static LicenceNumber ln = LicenceNumber.valueOf("JC-1990-66");
	private static Date dateOfIssue;
	private static boolean isRented = false;

	@Test
	public void testConstructor() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, dateOfBirth, ln, dateOfIssue, isRented);
		assertEquals(name.toString(), dl.getName().toString());
		assertEquals(dateOfBirth.getTime(), dl.getDateOfBirth().getTime());
		assertEquals(ln.toString(), dl.getLicenceNumber().toString());
		assertEquals(dateOfIssue.getTime(), dl.getDateOfIssue().getTime());
		assertEquals(isRented, dl.isRented());
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNameException() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(null, dateOfBirth, ln, dateOfIssue, isRented);
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorDateOfBirthException() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, null, ln, dateOfIssue, isRented);
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorLicenceException() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, dateOfBirth, null, dateOfIssue, isRented);
		RentalCompany.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorDateOfIssueException() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, dateOfBirth, ln, null, isRented);
		RentalCompany.clear();
	}

	@Test
	public void testIssueAndTerminate() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, dateOfBirth, ln, dateOfIssue, isRented);
		assertEquals(true, dl.issue());
		assertEquals(true, dl.isRented());
		assertEquals(false, dl.issue());
		assertEquals(true, dl.isRented());
		assertEquals(true, dl.terminateRental());
		assertEquals(false, dl.isRented());
		assertEquals(false, dl.terminateRental());
		RentalCompany.clear();
	}

	@Test
	public void testToString() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = new DriverLicence(name, dateOfBirth, ln, dateOfIssue, isRented);
		assertEquals(name+"-"+dateOfBirth.getTime()+"-"+ln+"-"+dateOfIssue.getTime()+"-"+isRented, dl.toString());
		RentalCompany.clear();
	}

	@Test
	public void testGetInstance() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = DriverLicence.getInstance(name, dateOfBirth, ln, dateOfIssue, isRented);
		DriverLicence dl1 = DriverLicence.getInstance(ln);
		dl1 = DriverLicence.getInstance(name, dateOfBirth, ln, dateOfIssue, isRented);
		assertEquals(true, dl1.equals(dl));
		RentalCompany.clear();
	}

	@Test
	public void testIssueAndTerminateTwo() {
		cl.set(1995, 4, 1);
		dateOfIssue = cl.getTime();
		dl = DriverLicence.getInstance(name, dateOfBirth, ln, dateOfIssue, isRented);
		assertEquals(true, DriverLicence.issue(dl));
		assertEquals(true, DriverLicence.getInstance(ln).isRented());
		assertEquals(true, DriverLicence.terminateRental(dl));
		assertEquals(false, DriverLicence.getInstance(ln).isRented());
		RentalCompany.clear();
	}
}
