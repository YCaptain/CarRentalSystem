package company;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import car.Car;
import car.CarFactory;
import driver.DriverLicence;
import driver.DriverLicence;

public class RentalCompany {
	// <key, value> matches registration number of cars and driver' licence
	private static final Map<Car, DriverLicence> rentedList = new HashMap<Car, DriverLicence>();
	private static RentalCompany company;

	private RentalCompany() {
	}

	public static RentalCompany getInstance() {
		if (company == null)
			company = new RentalCompany();
		return company;
	}

	public int availableCars(String carType) {
		return CarFactory.availableCars(carType);
	}

	public Collection<Car> getRentedCars() {
		return Arrays.asList(((Car[])rentedList.keySet().toArray()));
	}

	public Car getCar(DriverLicence driverLicence) {
		final Iterator<Entry<Car, DriverLicence>> eIter = rentedList.entrySet().iterator();
		while (eIter.hasNext()) {
			Entry<Car, DriverLicence> cd = eIter.next();
			if (cd.getValue().equals(driverLicence))
				return CarFactory.valueOf(cd.getKey().toString());
		}
		return null;
	}

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

	public int terminateRental(DriverLicence driverLicence) {
		final Iterator<Entry<Car, DriverLicence>> eIter = rentedList.entrySet().iterator();
		boolean find = false;
		Entry<Car, DriverLicence> entry = eIter.next();
		while (eIter.hasNext()) {
			DriverLicence dl = entry.getValue();
			if (dl.equals(driverLicence)) {
				find = true;
				break;
			}
			entry = eIter.next();
		}
		if (!find)
			return 0;
		final Car car = entry.getKey();
		car.terminateRental();
		driverLicence.terminateRental();
		rentedList.put(car, driverLicence);
		CarFactory.terminateRental(car);
		DriverLicence.terminateRental(driverLicence);

		return car.capacity() - car.fuel();
	}
}
