package org.prog.manager;

import java.util.*;

public class PoliceStation {

    private final List<Car> availableCars;
    private final List<Owner> owners;

    private final HashMap<Car, Owner> ownedCars;

    public PoliceStation() {
        availableCars = new ArrayList<>();
        owners = new ArrayList<>();
        ownedCars = new HashMap<>();
    }

    public void registerCar(Car car) {
        availableCars.add(car);
    }

    public void registerOwner(Owner owner) {
        owners.add(owner);
    }

    public void assignOwner(Car car, Owner owner) {
        if (availableCars.contains(car)) {
            availableCars.remove(car);
            ownedCars.put(car, owner);
        } else {
            throw new RuntimeException("Car is already owned by someone!");
        }
        System.out.println("Cars left unassigned: " + availableCars.size());
    }

    public void printOwner(Car car) {
        Owner owner = ownedCars.get(car);
        System.out.println(owner.getName() + " " + owner.getLastName());
    }

    public void printCars(Owner owner) {
        Set<Map.Entry<Car, Owner>> ownedVehicles = ownedCars.entrySet();
        List<Car> carsOwnedByPerson = new ArrayList<>();

        for (Map.Entry<Car, Owner> entry : ownedVehicles) {
            if (entry.getValue().equals(owner)) {
                carsOwnedByPerson.add(entry.getKey());
            }
        }

        for (Car c : carsOwnedByPerson) {
            System.out.println(c.getSerialNumber());
        }
    }

    public void printAvailableCars() {
        if (!availableCars.isEmpty()) {
            for (Car car : availableCars) {
                System.out.println(car);
            }
        } else {
            System.out.println("no cars available at the moment!");
        }
    }

    public void printAllCarsThatHaveOwners() {
        if (!ownedCars.isEmpty()) {
            for (Car car : ownedCars.keySet()) {
                System.out.println(car);
            }
        } else {
            System.out.println("no cars assigned at the moment!");
        }
    }

    //TODO: make this method return owner of car with plate number
    //TODO: if car not assigned -> exception
    //TODO: add test cases
    public Car printOwnerOfACarWithCertainPlateNumbers(String plateNumber) {
        boolean carIsAvailable = isPlateNumberPresent(availableCars, plateNumber);
        boolean carIsOwned = isPlateNumberPresent(ownedCars.keySet(), plateNumber);

        if (!(carIsAvailable || carIsOwned)) {
            throw new RuntimeException("No such plate number found! " + plateNumber);
        }

        if (carIsAvailable && carIsOwned) {
            throw new RuntimeException("Car is in both lists! " + plateNumber);
        }

        if (carIsAvailable) {
            throw new RuntimeException("Car with  " + plateNumber + " is not assigned");
        } else {
            Car car = getCarByPlateNumber(ownedCars.keySet(), plateNumber);
            System.out.println(ownedCars.get(car));
            return car;
        }
    }

    private String getCarPlates(Car car) {
        return car.getPlateNumber();
    }

    public List<Car> getCarsOwnedByPerson(String name) {
        if (owners.isEmpty()) {
            throw new RuntimeException("No owners registered!");
        }

        Owner owner = null;
        for (Owner o : owners) {
            if (o.getName().equals(name)) {
                owner = o;
                break;
            }
        }

        if (owner != null) {
            List<Car> ownedCars = new ArrayList<>();
            for (Map.Entry<Car, Owner> entry : this.ownedCars.entrySet()) {
                if (entry.getValue().equals(owner)) {
                    ownedCars.add(entry.getKey());
                }
            }
            return ownedCars;
        } else {
            throw new RuntimeException("No owner with name " + name + " found!");
        }
    }

    private boolean isPlateNumberPresent(Collection<Car> cars, String plateNumber) {
        for (Car car : cars) {
            if (getCarPlates(car).equals(plateNumber)) {
                return true;
            }
        }
        return false;
    }

    private Car getCarByPlateNumber(Collection<Car> cars, String plateNumber) {
        for (Car car : cars) {
            if (getCarPlates(car).equals(plateNumber)) {
                return car;
            }
        }
        throw new RuntimeException("You're doing wrong!");
    }
}
