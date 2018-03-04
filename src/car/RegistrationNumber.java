package car;

/**
 * Registration number - registration number of a car.
 *
 * @author YCaptain
 *
 */
public final class RegistrationNumber {
	private char letter;
	private int num;

	/**
	 * Construct a registration number from the given letter and num.
	 *
	 * @param letter a single letter.
	 * @param num a four digit number.
	 * @throws NullPointerException if
	 * <code>letter</code> is null
	 * @throws IllegalArgumentException if
	 * <code>letter</code> is not a letter or <code>num</code> is
	 * not a four digit number.
	 */
	public RegistrationNumber(char letter, int num) {
		setLetter(letter);
		setNum(num);
	}

	/**
	 * Return the letter part of the registration number.
	 *
	 * @return the letter part of the registration number.
	 */
	public char getLetter() {
		return letter;
	}

	/**
	 * Return the number part of the registration number.
	 *
	 * @return the number part of the registration number.
	 */
	public int getNum() {
		return num;
	}

	/**
	 * Set the letter part of the registration number.
	 *
	 * @param letter a single letter
	 * @throws NullPointerException if
	 * <code>letter</code> is null
	 * @throws IllegalArgumentException if
	 * <code>letter</code> is not a letter
	 */
	public void setLetter(char letter) {
		if (letter < 'a' || letter > 'z')
			throw new IllegalArgumentException("Not a letter");

		this.letter = letter;
	}

	/**
	 * Set the number part of the registration number.
	 *
	 * @param num a four digit number.
	 * @throws NullPointerException if
	 * <code>num</code> is null
	 * @throws IllegalArgumentException if
	 * <code>num</code> is not a four digit number
	 */
	public void setNum(int num) {
		if (num < 1000 || num > 9999)
			throw new IllegalArgumentException("Not a four digit number");

		this.num = num;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof RegistrationNumber))
			return false;

		final RegistrationNumber rn = (RegistrationNumber) obj;

		// note: letter and num are guaranteed
		return letter == rn.letter && num == rn.num;
	}

	/**
	 * Returns a string representation of a registration number.
	 * The string representation is a single letter and a four digit
	 * number.
	 *
	 * @see java.lang.Object#toString()
	 * @see #valueOf for the string representation of
	 * a registration number
	 */
	@Override
	public String toString() {
		return letter + "" + num;
	}

	/**
	 * Constructs an instance of RegistrationNumber from its
	 * string representation. The string representation
	 * of a RegistrationNumber is a single letter and a four digit
	 * number.
	 *
	 * @param registrationNumber registration number
	 * @return an instance of a RegistrationNumber corresponding the
	 * given string
	 * @throws NullPointerException if <code>registrationNumber</code> is null
	 */
	public static RegistrationNumber valueOf(String registrationNumber) {
		return new RegistrationNumber(registrationNumber.charAt(0), Integer.parseInt(registrationNumber.substring(1)));
	}
}
