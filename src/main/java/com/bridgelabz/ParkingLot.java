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
    private Vehicle vehicle;

    private ParkingLotOwner owner;
    private final int maxCapacity;
    private int currentCapacity;
    private final LinkedList<Vehicle> vehicles = new LinkedList<>();

    public ParkingLot(int maxCapacity) {
        this.currentCapacity = 0;
        this.maxCapacity = maxCapacity;
    }

    /**
     * @param vehicle -> Required to park the given vehicle.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (currentCapacity == maxCapacity && this.owner != null) {
            this.owner.capacityFull();
            throw new ParkingLotException("Parking lot full");
        }
        if (this.vehicle != null && this.vehicle.equals(vehicle)) {
            throw new ParkingLotException("Vehicle already parked");
        }
        currentCapacity++;
        this.vehicle = vehicle;
        vehicles.add(vehicle);
    }

    /**
     * @param vehicle -> Required to un-park the given vehicle.
     * @return -> Returns boolean by checking if the given vehicle exists.
     */
    public boolean unPark(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        for (Vehicle vehicle1 : vehicles) {
            if (vehicle.equals(vehicle1)) {
                vehicles.remove(vehicle1);
                return true;
            }
        }
        return false;
    }

    /**
     * Purpose -> This method registers the owner for the parking lot
     * @param owner -> Required to set the owner
     */
    public void registerOwner(ParkingLotOwner owner) {
        this.owner = owner;
    }

    /**
     * @param vehicle -> Required to get the current status of the given vehicle.
     * @return -> Return true if the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return this.vehicle == vehicle;
    }
}
