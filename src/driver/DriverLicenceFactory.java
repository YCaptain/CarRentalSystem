package driver;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DriverLicenceFactory implements DriverLicence {
	private static final Map<String, DriverLicence> licences = new HashMap<String, DriverLicence>();
	private final Name name;
	private final Date dateOfBirth;
	private final LicenceNumber licenceNumber;
	private final Date dateOfIssue;
	private boolean isRented;

	DriverLicenceFactory(Name name, Date dateOfBirth, LicenceNumber licenceNumber, Date dateOfIssue, boolean isRented) {
		if (name == null)
			throw new IllegalArgumentException("name is null");
		if (dateOfBirth == null)
			throw new IllegalArgumentException("date of birth is null");
		if (licenceNumber == null)
			throw new IllegalArgumentException("licence number is null");
		if (dateOfIssue == null)
			throw new IllegalArgumentException("date of issue is null");
		this.name = Name.valueOf(name.toString());
		this.dateOfBirth = new Date(dateOfBirth.getTime());
		this.licenceNumber = LicenceNumber.valueOf(licenceNumber.toString());
		this.dateOfIssue = new Date(dateOfIssue.getTime());
		this.isRented = isRented;
	}

	public static DriverLicence getInstance(Name name, Date dateOfBirth, LicenceNumber licenceNumber, Date dateOfIssue,
			boolean isRented) {
		// enforce single instance per licenceNumber
		final String licenceNum = licenceNumber.toString();
		DriverLicence dl = licences.get(licenceNum);

		if (dl != null)
			return valueOf(dl.toString());

		dl = new DriverLicenceFactory(name, dateOfBirth, licenceNumber, dateOfIssue, isRented);

		// put dl in the licences map
		licences.put(licenceNum, dl);

		// return the instance
		return valueOf(dl.toString());
	}

	public static DriverLicence getInstance(LicenceNumber licenceNumber) {
		final String ln = licenceNumber.toString();
		DriverLicence dl = licences.get(ln);
		if (dl != null)
			return valueOf(dl.toString());
		return null;
	}

	@Override
	public Name getName() {
		return new Name(name.getFirstName(), name.getLastName());
	}

	@Override
	public Date getDateOfBirth() {
		return new Date(dateOfBirth.getTime());
	}

	@Override
	public LicenceNumber getLicenceNumber() {
		return new LicenceNumber(licenceNumber.getFirst(), licenceNumber.getSecond(), licenceNumber.getThird());
	}

	@Override
	public Date getDateOfIssue() {
		return new Date(dateOfIssue.getTime());
	}

	@Override
	public boolean isRented() {
		return isRented;
	}

	@Override
	public boolean issue() {
		if (isRented)
			return false;
		isRented = !isRented;
		return true;
	}

	public static boolean issue(DriverLicence driverLicence) {
		final Iterator<DriverLicence> licIter = licences.values().iterator();
		while (licIter.hasNext()) {
			DriverLicence dl = licIter.next();
			dl.issue();
			licences.put(dl.getLicenceNumber().toString(), dl);
			return true;
		}
		return false;
	}

	@Override
	public boolean terminateRental() {
		if (!isRented)
			return false;
		isRented = !isRented;
		return true;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof DriverLicence))
			return false;

		final DriverLicenceFactory lic = (DriverLicenceFactory) obj;

		return name.equals(lic.name) && dateOfBirth.equals(lic.dateOfBirth) && licenceNumber.equals(lic.licenceNumber)
				&& dateOfIssue.equals(lic.dateOfIssue) && isRented == lic.isRented;
	}

	@Override
	public String toString() {
		return name + "-" + dateOfBirth.getTime() + "-" + licenceNumber + "-" + dateOfIssue.getTime() + "-" + isRented;
	}

	public static DriverLicence valueOf(String str) {
		final String[] parts = str.split("-");
		return DriverLicenceFactory.getInstance(Name.valueOf(parts[0]), new Date(Long.valueOf(parts[1])),
				LicenceNumber.valueOf(parts[2]), new Date(Long.valueOf(parts[3])), Boolean.parseBoolean(parts[4]));
	}

}
