package car;

public class SmallCar extends CarFactory {
	/**
	 * the capacity of the small car's fuel tank
	 */
	public static final int CAP = 49;
	private static final int FUEL_RATE = 20;

	/**
	 * @see CarFactory#CarFactory(String)
	 */
	public SmallCar(RegistrationNumber regisNum) {
		super(regisNum, CAP);
	}

	public SmallCar(RegistrationNumber regisNum, int fuel, boolean isRented) {
		super(regisNum, CAP, fuel, isRented);
	}

	@Override
	public int drive(int dist) {
		if (dist < 0)
			throw new IllegalArgumentException("Negative distance: " + dist);
		final int fuel = fuel();
		if (!isRented() || fuel <= 0)
			return -1;
		double dd = dist;
		final int costFuel = (int) Math.ceil(dd / FUEL_RATE);
		setFuel(fuel - costFuel);
		update(this);
		return costFuel;
	}

}
