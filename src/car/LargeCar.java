package car;

public class LargeCar extends CarFactory{
	/**
	 * the capacity of the large car's fuel tank
	 */
	private static final int CAP = 60;
	private static final int FUEL_RATE_FIR = 10;
	private static final int FUEL_RATE_SEC = 15;

	public LargeCar(RegistrationNumber regisNum) {
		super(regisNum, CAP);
	}

	@Override
	public int drive(int dist) {
		if (dist < 0)
			throw new IllegalArgumentException("Negative distance: " + dist);
		final int fuel = fuel();
		if (isRented() || fuel < 0)
			return -1;
		final int costFuel;
		if (dist <= 50) {
			costFuel = (int) Math.ceil(FUEL_RATE_FIR / dist);
		} else {
			costFuel = (int) Math.ceil(FUEL_RATE_FIR / 50) + (int) Math.ceil(FUEL_RATE_SEC / (dist-50));
		}
		setFuel(fuel - costFuel);
		return costFuel;
	}

}
