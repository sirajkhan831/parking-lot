package com.bridgelabz;

import java.util.HashMap;
import java.util.Map;

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
    private final int maxCapacity;
    private boolean isHandicapped;
    private final HashMap<Integer, Vehicle> vehicles = new HashMap<>();
    private final HashMap<Integer, String> vehicleTimeStamp = new HashMap<>();

    public ParkingLot(int maxCapacity, ParkingLotOwner owner, AirportSecurity airportSecurity) {
        this.maxCapacity = setMaxCapacity(maxCapacity);
        this.owner = owner;
        this.airportSecurity = airportSecurity;
        int index = 0;
        for (int i = 0; i < this.maxCapacity; i++) {
            vehicles.put(index, null);
            vehicleTimeStamp.put(index, null);
            index++;
        }
    }

    /**
     * Purpose -> This method parks a vehicle in the parking lot.
     *
     * @param vehicle -> Required to park the given vehicle.
     */
    public void park(Vehicle vehicle, String timeStamp) throws ParkingLotException {
        if (isVehicleParked(vehicle)) {
            throw new ParkingLotException("Already parked");
        }
        if (isHandicapped) {
            if (vehicles.get(0) == null) {
                vehicles.put(0, vehicle);
                vehicleTimeStamp.put(0, timeStamp);
            } else {
                isHandicapped = false;
            }
        }
        for (int i = 1; i < vehicles.size(); i++) {
            if (vehicles.get(i) == null) {
                vehicles.put(i, vehicle);
                vehicleTimeStamp.put(i, timeStamp);
                break;
            }
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
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(vehicle)) {
                vehicles.put(entry.getKey(), null);
                vehicleTimeStamp.put(entry.getKey(), null);
            }
        }
    }

    /**
     * Purpose -> This method registers the owner for the parking lot
     *
     * @param owner -> Required to set the owner
     */
    public void registerObserver(ParkingLotOwner owner) {
        this.owner = owner;
    }

    /**
     * Purpose -> This method registers the airport security for the parking lot
     *
     * @param airportSecurity -> Required to set the airport security
     */
    public void registerObserver(AirportSecurity airportSecurity) {
        this.airportSecurity = airportSecurity;
    }

    /**
     * Purpose -> This method checks if the given vehicle is parked or not.
     *
     * @param vehicle -> Required to get the current status of the given vehicle.
     * @return -> Return true if the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicles.containsValue(vehicle);
    }

    /**
     * Purpose -> This method sets the max capacity of the parking lot.
     *
     * @param maxCapacity -> Required to set the max capacity of the parking lot.
     */
    public int setMaxCapacity(int maxCapacity) {
        return maxCapacity;
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

    /**
     * Purpose -> This method is used to locate the vehicle in the lot.
     *
     * @param vehicle -> Required to check the given vehicle position
     * @return -> Returns vehicle position
     */
    public int getVehiclePosition(Vehicle vehicle) {
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            if (entry.getValue() != null && entry.getValue().equals(vehicle)) {
                return (entry.getKey());
            }
        }
        return -1;
    }

    /**
     * @param vehicle -> Required to check the given vehicle position
     * @return -> Returns vehicle timestamp
     */
    public String getTimeStamp(Vehicle vehicle) {
        return vehicleTimeStamp.get(getVehiclePosition(vehicle));
    }

    public void setHandicapped(boolean handicapped) {
        isHandicapped = handicapped;
    }
}
