package company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import car.Car;
import car.CarFactory;
import driver.DriverLicence;

public class RentalCompany {
	// <key, value> matches registration number of cars and driver' licence
	private static final Map<Car, DriverLicence> rentedList = new HashMap<Car, DriverLicence>();
	private static RentalCompany company;

	private RentalCompany() {
	}

	/**
	 * Returns a rental company by factory which guarantee that only a instance existed.
	 *
	 * @return a rental company by factory which guarantee that only a instance existed.
	 */
	public static RentalCompany getInstance() {
		if (company == null)
			company = new RentalCompany();
		return company;
	}

	/**
	 * Returns the number of cars of the specified type
	 * that are available to rent.
	 *
	 * @param carType car type
	 * @return the number of cars of the specified type
	 * that are available to rent.
	 * @throws IllegalArgumentException if <code>carType</code>
	 * is a invalid car type.
	 */
	public int availableCars(String carType) {
		return CarFactory.availableCars(carType);
	}

	/**
	 * Returns a collection of all the cars currently
	 * rented out.
	 * @return a collection of all the cars currently
	 * rented out.
	 */
	public Collection<Car> getRentedCars() {
		Collection<Car> toReturn = new ArrayList<Car>();
		Iterator<Car> cIter = rentedList.keySet().iterator();
		while (cIter.hasNext()) {
			Car car = cIter.next();
			toReturn.add(car);
		}
		return toReturn;
	}

	/**
	 * Returns the car by given a person's driving licence.
	 * @param driverLicence driver's licence.
	 * @return the car by given a person's driving licence.
	 */
	public Car getCar(DriverLicence driverLicence) {
		final Iterator<Entry<Car, DriverLicence>> eIter = rentedList.entrySet().iterator();
		while (eIter.hasNext()) {
			Entry<Car, DriverLicence> cd = eIter.next();
			if (cd.getValue().equals(driverLicence))
				return CarFactory.valueOf(cd.getKey().toString());
		}
		return null;
	}

	/**
	 * Returns whether the issue operation is success or not.
	 * Given a person's driving licence and a specification of the
	 * type of car required, determines whether the person is eligible
	 * to rent a car of the specified type and issues a car of
	 * the specified type.
	 * @param driverLicence driver's licence
	 * @param carType car type
	 * @return whether the issue operation is success or not.
	 */
	public boolean issueCar(DriverLicence driverLicence, String carType) {
		final Iterator<DriverLicence> dlIter = rentedList.values().iterator();
		while (dlIter.hasNext()) {
			DriverLicence dl = dlIter.next();
			if (dl.equals(driverLicence))
				return false;
		}

		if (availableCars(carType) == 0)
			return false;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final String[] birth = format.format(driverLicence.getDateOfBirth()).split("-");
		final String[] issueLic = format.format(driverLicence.getDateOfIssue()).split("-");
		final String[] now = format.format(new Date()).split("-");
		int age = Integer.parseInt(now[0]) - Integer.parseInt(birth[0]);
		int heldLic = Integer.parseInt(now[0]) - Integer.parseInt(issueLic[0]);
		if (Integer.parseInt(birth[1]) > Integer.parseInt(now[1]) || (Integer.parseInt(birth[1]) == Integer.parseInt(now[1]) &&
				Integer.parseInt(birth[2]) > Integer.parseInt(now[2])))
			age--;
		if (Integer.parseInt(issueLic[1]) > Integer.parseInt(now[1]) || (Integer.parseInt(issueLic[1]) == Integer.parseInt(now[1]) &&
				Integer.parseInt(issueLic[2]) > Integer.parseInt(now[2])))
			heldLic--;

		if (carType.equals(CarFactory.SMALL_CAR)) {
			if (age < 21 || heldLic < 1)
				return false;
		} else if (carType.equals(CarFactory.LARGE_CAR)) {
			if (age < 25 || heldLic < 5)
				return false;
		}

		// issue car
		final Car car = CarFactory.getCarByType(carType);
		car.issue();
		driverLicence.issue();
		rentedList.put(car, driverLicence);
		CarFactory.issue(car);
		DriverLicence.issue(driverLicence);

		return true;
	}

	/**
	 * Returns the amount of fuel in Liters required to fill the
	 * car's tank. Terminates the rental contract associated with
	 * the given driving licence.
	 * @param driverLicence driver's licence.
	 * @return the amount of fuel in Liters required to fill the
	 * car's tank.
	 */
	public int terminateRental(DriverLicence driverLicence) {
		final Iterator<Entry<Car, DriverLicence>> eIter = rentedList.entrySet().iterator();
		while (eIter.hasNext()) {
			Entry<Car, DriverLicence> entry = eIter.next();
			DriverLicence dl = entry.getValue();
			if (dl.equals(driverLicence)) {
				final Car car = entry.getKey();
				car.terminateRental();
				driverLicence.terminateRental();
				rentedList.put(car, driverLicence);
				rentedList.remove(car);
				CarFactory.terminateRental(car);
				DriverLicence.terminateRental(driverLicence);
				return car.capacity() - car.fuel();
			}
		}
		return -1;

	}

	/**
	 * Returns how much fuel to refuel by given car and amount of fuel.
	 * @param car car to refuel
	 * @param nfuel amount of fuel
	 * @return how much fuel to refuel by given car and amount of fuel.
	 */
	public static int refuel(Car car, int nfuel) {
		final Iterator<Entry<Car, DriverLicence>> rIter = rentedList.entrySet().iterator();
		int d = 0;
		while (rIter.hasNext()) {
			final Entry<Car, DriverLicence> entry = rIter.next();
			Car c = entry.getKey();
			if (c.equals(car)) {
				d = c.refuel(nfuel);
				rentedList.put(c, entry.getValue());
			}
		}
		return d;
	}

	/**
	 * @see car.Car#drive()
	 */
	public int drive(Car car, int dist) {
		final Iterator<Entry<Car, DriverLicence>> rIter = rentedList.entrySet().iterator();
		int d = 0;
		while (rIter.hasNext()) {
			final Entry<Car, DriverLicence> entry = rIter.next();
			Car c = entry.getKey();
			if (c.equals(car)) {
				d = c.drive(dist);
				rentedList.put(c, entry.getValue());
			}
		}
		return d;
	}

	/**
	 * Clear records of company, car factory and driver licence.
	 */
	public static void clear() {
		rentedList.clear();
		CarFactory.clear();
		DriverLicence.clear();
	}
}
