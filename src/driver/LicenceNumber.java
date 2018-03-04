package driver;

/**
 * LicenceNumber - licence number of a driver.
 *
 * @author YCaptain
 *
 */
public final class LicenceNumber {
	private String first;
	private int second;
	private int third;

	/**
	 * Construct a licence number from the given first, second and last.
	 *
	 * @param first
	 *            the first component of the licence number.
	 * @param second
	 *            the second component of the licence number.
	 * @param third
	 *            the third component of the licence number.
	 * @throws NullPointerException
	 *             if either <code>first</code>, <code>second</code> or
	 *             <code>third</code> is null
	 * @throws IllegalArgumentException
	 *             if either <code>first</code>, <code>second</code> or
	 *             <code>third</code> is empty
	 */
	public LicenceNumber(String first, int second, int third) {
		setFirst(first);
		setSecond(second);
		setThird(third);
	}

	/**
	 * Return the first component of the licence number.
	 *
	 * @return the first component of the licence number.
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Return the second component of the licence number.
	 *
	 * @return the second component of the licence number.
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Return the third component of the licence number.
	 *
	 * @return the third component of the licence number.
	 */
	public int getThird() {
		return third;
	}

	/**
	 * Set the first component of the licence number.
	 *
	 * @param first
	 *            the first component of the licence number.
	 * @throws NullPointerException
	 *             if <code>first</code> is null
	 * @throws IllegalArgumentException
	 *             if <code>first</code> is empty
	 */
	public void setFirst(String first) {
		if (first.length() == 0)
			throw new IllegalArgumentException("Empty first component");

		this.first = first;
	}

	/**
	 * Set the second component of the licence number.
	 *
	 * @param second
	 *            the second component of the licence number.
	 * @throws NullPointerException
	 *             if <code>second</code> is null
	 * @throws IllegalArgumentException
	 *             if <code>second</code> is empty
	 */
	public void setSecond(int second) {
		if (second == 0)
			throw new IllegalArgumentException("Empty second component");

		this.second = second;
	}

	/**
	 * Set the third component of the licence number.
	 *
	 * @param third
	 *            the third component of the licence number.
	 * @throws NullPointerException
	 *             if <code>third</code> is null
	 * @throws IllegalArgumentException
	 *             if <code>third</code> is empty
	 */
	public void setThird(int third) {
		if (third == 0)
			throw new IllegalArgumentException("Empty third component");

		this.third = third;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof LicenceNumber))
			return false;

		final LicenceNumber ln = (LicenceNumber) obj;

		return toString().equals(ln.toString());
	}

	/**
	 * Returns a string representation of a licence number. The string
	 * representation is a first part, second part and third part separated by a "-"
	 * signal.
	 *
	 * @see java.lang.Object#toString()
	 * @see #valueOf for the string representation of a licence number.
	 */
	@Override
	public String toString() {
		return first + "-" + second + "-" + third;
	}

	/**
	 * Constructs an instance of LicenceNumber from its
	 * string representation. The string representation is
	 * a first part, second part and third part separated
	 * by a "-" signal.
	 *
	 * @param licenceNumber a licence number in the specified
	 * string representation
	 * @return an instance of a LicenceNumber corresponding to
	 * the given string
	 * @throws NullPointerException if <code>licenceNumber</code> is null
	 * @throws ArrayIndexOutOfBoundsException if there are not
	 * three component parts to <code>licenceNumber</code> (first, second
	 * and third parts)
	 */
	public static LicenceNumber valueOf(String licenceNumber) {
		final String[] parts = licenceNumber.split("-");

		return new LicenceNumber(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
	}
}
