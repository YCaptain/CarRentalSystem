package driver;

import java.util.Date;

/**
 * DriverLicence - interface to driver licences.
 *
 * @author YCaptain
 *
 */
public interface DriverLicence {
	/**
	 * Returns the driver's name
	 * which comprising a first and last name.
	 *
	 * @return the driver's name
	 */
	public Name getName();

	/**
	 * Returns the date of birth of the driver.
	 *
	 * @return the date of birth of the driver.
	 */
	public Date getDateOfBirth();

	/**
	 * Returns the unique licence number of the driver.
	 *
	 * @return the unique licence number of the driver.
	 */
	public LicenceNumber getLicenceNumber();

	/**
	 * Returns the date of issue.
	 *
	 * @return the date of issue.
	 */
	public Date getDateOfIssue();

	/**
	 * Returns whether the licence is a full driving licence or not.
	 *
	 * @return
	 */
	public boolean isRented();

	public boolean issue();

	public boolean terminateRental();
}
