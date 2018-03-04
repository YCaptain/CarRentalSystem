package car;

public class LargeCar extends CarFactory{
	/**
	 * the capacity of the large car's fuel tank
	 */
	public static final int CAP = 60;
	private static final int FUEL_RATE_FIR = 10;
	private static final int FUEL_RATE_SEC = 15;

	public LargeCar(RegistrationNumber regisNum) {
		super(regisNum, CAP);
	}

	public LargeCar(RegistrationNumber regisNum, int fuel, boolean isRented) {
		super(regisNum, CAP, fuel, isRented);
	}


	@Override
	public int drive(int dist) {
		if (dist < 0)
			throw new IllegalArgumentException("Negative distance: " + dist);
		final int fuel = fuel();
		if (!isRented() || fuel < 0)
			return -1;
		final int costFuel;
		final double dd = dist;
		if (dist <= 50) {
			costFuel = (int) Math.ceil(dd / FUEL_RATE_FIR);
		} else {
			costFuel = (int) Math.ceil(50 / FUEL_RATE_FIR) + (int) Math.ceil((dd-50) / FUEL_RATE_SEC);
		}
		setFuel(fuel - costFuel);
		update(this);
		return costFuel;
	}

}
