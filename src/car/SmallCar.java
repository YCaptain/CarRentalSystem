package car;

public class SmallCar extends CarFactory {
	/**
	 * the capacity of the small car's fuel tank
	 */
	private static final int CAP = 49;
	private static final int FUEL_RATE = 20;

	/**
	 * @see CarFactory#CarFactory(String)
	 */
	public SmallCar(RegistrationNumber regisNum) {
		super(regisNum, CAP);
	}

	@Override
	public int drive(int dist) {
		if (dist < 0)
			throw new IllegalArgumentException("Negative distance: " + dist);
		final int fuel = fuel();
		if (isRented() || fuel < 0)
			return -1;
		final int costFuel = (int) Math.ceil(FUEL_RATE / dist);
		setFuel(fuel - costFuel);
		return costFuel;
	}

}
