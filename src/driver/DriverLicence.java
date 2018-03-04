package driver;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * DriverLicence - interface to driver licences.
 *
 * @author YCaptain
 *
 */
public class DriverLicence{
	private static final Map<String, DriverLicence> licences = new HashMap<String, DriverLicence>();
	private final Name name;
	private final Date dateOfBirth;
	private final LicenceNumber licenceNumber;
	private final Date dateOfIssue;
	private boolean isRented;

	DriverLicence(Name name, Date dateOfBirth, LicenceNumber licenceNumber, Date dateOfIssue, boolean isRented) {
		if (name == null)
			throw new IllegalArgumentException("name is null");
		else if (dateOfBirth == null)
			throw new IllegalArgumentException("date of birth is null");
		else if (licenceNumber == null)
			throw new IllegalArgumentException("licence number is null");
		else if (dateOfIssue == null)
			throw new IllegalArgumentException("date of issue is null");
		this.name = Name.valueOf(name.toString());
		this.dateOfBirth = new Date(dateOfBirth.getTime());
		this.licenceNumber = LicenceNumber.valueOf(licenceNumber.toString());
		this.dateOfIssue = new Date(dateOfIssue.getTime());
		this.isRented = isRented;
	}

	/**
	 * Returns a driver licence by given information.
	 * @param name the name of the driver
	 * @param dateOfBirth the date of birth of the driver
	 * @param licenceNumber the licence number
	 * @param dateOfIssue the date of issue of the licence
	 * @param isRented whether the driver has rented a car
	 * @return a driver licence by given information.
	 * @throws NullPointerException if name, dateOfBirth or other parameters is null
	 * @throws IllegalArgumentException if name, dateOfBirth or other parameters is invalid
	 */
	public static DriverLicence getInstance(Name name, Date dateOfBirth, LicenceNumber licenceNumber, Date dateOfIssue,
			boolean isRented) {
		// enforce single instance per licenceNumber
		final String licenceNum = licenceNumber.toString();
		DriverLicence dl = licences.get(licenceNum);

		if (dl != null)
			return valueOf(dl.toString());

		dl = new DriverLicence(name, dateOfBirth, licenceNumber, dateOfIssue, isRented);

		// put dl in the licences map
		licences.put(licenceNum, dl);

		// return the instance
		return valueOf(dl.toString());
	}

	/**
	 * Returns a driver licence by licence number.
	 * @param licenceNumber the licence number of the licence
	 * @return a driver licence by licence number.
	 */
	public static DriverLicence getInstance(LicenceNumber licenceNumber) {
		final String ln = licenceNumber.toString();
		DriverLicence dl = licences.get(ln);
		if (dl != null)
			return valueOf(dl.toString());
		return null;
	}

	/**
	 * Returns the driver's name
	 * which comprising a first and last name.
	 *
	 * @return the driver's name
	 */
	public Name getName() {
		return new Name(name.getFirstName(), name.getLastName());
	}

	/**
	 * Returns the date of birth of the driver.
	 *
	 * @return the date of birth of the driver.
	 */
	public Date getDateOfBirth() {
		return new Date(dateOfBirth.getTime());
	}

	/**
	 * Returns the unique licence number of the driver.
	 *
	 * @return the unique licence number of the driver.
	 */
	public LicenceNumber getLicenceNumber() {
		return new LicenceNumber(licenceNumber.getFirst(), licenceNumber.getSecond(), licenceNumber.getThird());
	}

	/**
	 * Returns the date of issue.
	 *
	 * @return the date of issue.
	 */
	public Date getDateOfIssue() {
		return new Date(dateOfIssue.getTime());
	}

	/**
	 * Returns whether the licence is a full driving licence or not.
	 *
	 * @return whether the licence is a full driving licence or not.
	 */
	public boolean isRented() {
		return isRented;
	}

	/**
	 * Returns whether issue operation is success or not.
	 *
	 * @return whether issue operation is success or not.
	 */
	public boolean issue() {
		if (isRented)
			return false;
		isRented = true;
		return true;
	}

	/**
	 * Returns whether issue operation is success or not.
	 *
	 * @return whether issue operation is success or not.
	 */
	public static boolean issue(DriverLicence driverLicence) {
		final Iterator<DriverLicence> licIter = licences.values().iterator();
		while (licIter.hasNext()) {
			DriverLicence dl = licIter.next();
			if (!dl.equals(driverLicence))
				continue;
			dl.issue();
			licences.put(dl.getLicenceNumber().toString(), dl);
			return true;
		}
		return false;
	}

	/**
	 * Returns whether terminate operation is success or not.
	 *
	 * @return whether terminate operation is success or not.
	 */
	public boolean terminateRental() {
		if (!isRented)
			return false;
		isRented = false;
		return true;
	}

	/**
	 * Returns whether terminate operation is success or not.
	 *
	 * @return whether terminate operation is success or not.
	 */
	public static boolean terminateRental(DriverLicence driverLicence) {
		final Iterator<DriverLicence> licIter = licences.values().iterator();
		while (licIter.hasNext()) {
			DriverLicence dl = licIter.next();
			dl.terminateRental();
			licences.put(dl.getLicenceNumber().toString(), dl);
			return true;
		}
		return false;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof DriverLicence))
			return false;

		final DriverLicence lic = (DriverLicence) obj;

		return name.equals(lic.name) && dateOfBirth.equals(lic.dateOfBirth) && licenceNumber.equals(lic.licenceNumber)
				&& dateOfIssue.equals(lic.dateOfIssue) && isRented == lic.isRented;
	}

	/**
	 * Returns a string representation of a driver licence.
	 *
	 * @see java.lang.Object#toString()
	 * @see #valueOf for the string representation of
	 * a driver licence
	 */
	@Override
	public String toString() {
		return name + "-" + dateOfBirth.getTime() + "-" + licenceNumber + "-" + dateOfIssue.getTime() + "-" + isRented;
	}

	/**
	 * Constructs an instance of DriverLicence from its
	 * string representation.
	 *
	 * @param str string representation of DriverLicence
	 * @return an instance of DriverLicence from its
	 * string representation.
	 * @throws NullPointerException if <code>str</code> is null
	 * @throws ArrayIndexOutOfBoundsException if there are not
	 * six component parts to <code>str</code>
	 */
	public static DriverLicence valueOf(String str) {
		final String[] parts = str.split("-");
		return new DriverLicence(Name.valueOf(parts[0]), new Date(Long.valueOf(parts[1])),
				LicenceNumber.valueOf(parts[2]+"-"+parts[3]+"-"+parts[4]), new Date(Long.valueOf(parts[5])), Boolean.parseBoolean(parts[6]));
	}

	public static void clear() {
		licences.clear();
	}

}
