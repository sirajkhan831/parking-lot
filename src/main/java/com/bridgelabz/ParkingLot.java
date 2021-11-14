package com.bridgelabz;

import java.util.LinkedList;

/**
 * @author -> Siraj
 * @version -> 0.1
 * @since -> 10/11/2021
 */
public class ParkingLot {

    /**
     * This program manages parking spaces for vehicles.
     */
    private ParkingLotOwner owner;
    private AirportSecurity airportSecurity;
    private int maxCapacity;
    private final LinkedList<Vehicle> vehicles = new LinkedList<>();

    public ParkingLot(int maxCapacity, ParkingLotOwner owner, AirportSecurity airportSecurity) {
        this.maxCapacity = maxCapacity;
        this.owner = owner;
        this.airportSecurity = airportSecurity;
    }

    /**
     * Purpose -> This method parks a vehicle in the parking lot.
     *
     * @param vehicle -> Required to park the given vehicle.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        for (Vehicle currentVehicle : vehicles) {
            if (currentVehicle.equals(vehicle)) {
                throw new ParkingLotException("Vehicle already Parked");
            }
        }
        if (vehicles.size() < maxCapacity) {
            vehicles.add(vehicle);
        }
        if (vehicles.size() == maxCapacity) {
            capacityFull(true);
        }
        if (vehicles.size() > maxCapacity) {
            throw new ParkingLotException("Parking lot is full");
        }
    }

    /**
     * Purpose -> This method un-parks an already parked vehicle
     *
     * @param vehicle -> Required to un-park the given vehicle.
     */
    public void unPark(Vehicle vehicle) {
        capacityFull(false);
        vehicles.removeIf(vehicle::equals);
    }

    /**
     * Purpose -> This method registers the owner for the parking lot
     *
     * @param owner -> Required to set the owner
     */
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }

    /**
     * Purpose -> This method registers the airport security for the parking lot
     *
     * @param airportSecurity -> Required to set the airport security
     */
    public void registerAirportSecurity(AirportSecurity airportSecurity) {
        this.airportSecurity = airportSecurity;
    }

    /**
     * Purpose -> This method checks if the given vehicle is parked or not.
     *
     * @param vehicle -> Required to get the current status of the given vehicle.
     * @return -> Return true if the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean vehicleExists = false;
        for (Vehicle currentVehicle : vehicles) {
            if (currentVehicle.equals(vehicle)) {
                vehicleExists = true;
                break;
            }
        }
        return vehicleExists;
    }

    /**
     * Purpose -> This method sets the max capacity of the parking lot.
     *
     * @param maxCapacity -> Required to set the max capacity of the parking lot.
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Purpose -> This method informs the owner and airport security about lot's current capacity.
     *
     * @param capacityCheck -> If true sets lot at max capacity
     */
    public void capacityFull(boolean capacityCheck) {
        owner.capacityFull(capacityCheck);
        airportSecurity.capacityFull(capacityCheck);
    }
}
