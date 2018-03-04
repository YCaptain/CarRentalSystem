package car;

public interface Car {
	/**
	 * Returns the registration number.
	 * All cars must have a registration number
	 * which uniquely identifies a car.
	 *
	 * @return the registration number
	 */
	public RegistrationNumber regisNum();

	/**
	 * Returns the capacity in whole Liters of the car's fuel tank.
	 *
	 * @return the capacity in whole Liters of the car's fuel tank.
	 */
	public int capacity();

	/**
	 * Returns the amount of fuel in whole Liters currently in the fuel tank.
	 *
	 * @return the amount of fuel in whole Liters currently in the fuel tank.
	 */
	public int fuel();

	/**
	 * Returns whether the car's fuel tank is full or not.
	 *
	 * @return whether the car's fuel tank is full or not.
	 */
	public boolean isFull();

	/**
	 * Returns whether the car is rented or not.
	 *
	 * @return whether the car is rented or not.
	 */
	public boolean isRented();

	/**
	 * Returns how much fuel was added to the tank
	 * (which will be 0 if the car is not rented or its tank is already full)
	 * Add a given number of whole Liters to the fuel tank.
	 * (up to the tank's capacity)
	 * @param nfuel the number of whole Liters added to the fuel tank.
	 * @throws IllegalArgumentException
	 * if <code>nfuel < 0</code>
	 * @return how much fuel was added to the tank
	 */
	public int refuel(int nfuel);

	/**
	 * Returns how much fuel was cost by driving a distance.
	 * @param dist distance to drive
	 * @return how much fuel was cost by driving a distance.
	 */
	public int drive(int dist);

	/**
	 * Returns whether issue operation is success or not.
	 *
	 * @return whether issue operation is success or not.
	 */
	public boolean issue();

	/**
	 * Returns whether terminate operation is success or not.
	 *
	 * @return whether terminate operation is success or not.
	 */
	public boolean terminateRental();
}
