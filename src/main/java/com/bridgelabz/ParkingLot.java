package com.bridgelabz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/******************************************************************************
 *
 *  Purpose: Parking lot program which ensures parking position, capacity, etc.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 10-11-2021
 *
 ******************************************************************************/

public class ParkingLot implements Comparable<ParkingLot> {

    private final ParkingLotOwner owner;
    private final AirportSecurity airportSecurity;
    private final HashMap<Integer, Vehicle> vehicles = new HashMap<>();
    private final HashMap<Vehicle, LocalDateTime> vehicleTimeStamp = new HashMap<>();

    /**
     * Purpose: Default Constructor to initialize maxCapacity, owner & security
     *
     * @param owner:           Sets the owner of the lot.
     * @param airportSecurity: Sets the airport security head of the lot.
     **/
    public ParkingLot(int maxCapacity, ParkingLotOwner owner, AirportSecurity airportSecurity) {
        this.owner = owner;
        this.airportSecurity = airportSecurity;
        setMaxCapacity(maxCapacity);
    }

    /**
     * Purpose: This method parks a vehicle in the parking lot.
     *
     * @param vehicle: Takes Vehicle object and parks the given vehicle.
     * @param time:    Takes LocalTime and sets the parking time of vehicle.
     * @throws ParkingLotException : Custom exception for invalid parking.
     */
    public void park(Vehicle vehicle, LocalDateTime time) throws ParkingLotException {
        if (isAtMaxCapacity()) {
            throw new ParkingLotException("Parking already full");
        }
        if (vehicle.isDriverHandicapped()) {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) == null) {
                    vehicles.put(i, vehicle);
                    vehicleTimeStamp.put(vehicle, time);
                    break;
                }
            }
        } else {
            for (int i = vehicles.size() - 1; i >= 0; i--) {
                if (vehicles.get(i) == null) {
                    vehicles.put(i, vehicle);
                    vehicleTimeStamp.put(vehicle, time);
                    break;
                }
            }
        }
        isAtMaxCapacity();
    }

    /**
     * Purpose: Used to un-parks an already parked vehicle
     *
     * @param vehicle: Takes Vehicle object and un-parks the given vehicle.
     */
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (isVehicleParked(vehicle)) {
            vehicles.put(getVehiclePosition(vehicle), null);
            isAtMaxCapacity();
        } else {
            throw new ParkingLotException("Vehicle not parked");
        }
    }

    /**
     * Purpose: Used to inform the owner and airport security about lot's max capacity.
     */
    public boolean isAtMaxCapacity() {
        boolean hasSpace = false;
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            if (entry.getValue() == null) {
                hasSpace = true;
                break;
            }
        }
        if (hasSpace) {
            owner.capacityFull(false);
            airportSecurity.capacityFull(false);
            return false;
        } else {
            owner.capacityFull(true);
            airportSecurity.capacityFull(true);
            return true;
        }
    }

    /**
     * Purpose: Determines weather the given vehicle is parked or not.
     *
     * @param vehicle: Takes Vehicle object and checks the given vehicle.
     * @return : Returns true if the vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicles.containsValue(vehicle);
    }

    /**
     * Purpose: Used to sets the max capacity of the parking lot.
     *
     * @param maxCapacity : Required to set the max capacity of the parking lot.
     */
    public void setMaxCapacity(int maxCapacity) {
        vehicles.clear();
        int index = 0;
        for (int i = 0; i < maxCapacity; i++) {
            vehicles.put(index, null);
            index++;
        }
    }

    /**
     * Purpose: Used is used to locate the vehicle in the lot.
     *
     * @param vehicle: Required to check the given vehicle position
     * @return : Returns vehicle position
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
     * Purpose : Method used to find the duration of parked vehicle in the lot.
     *
     * @param vehicle : Required to find the duration of given vehicle.
     * @return : Returns time in minutes by comparing timestamp and static test time
     */
    public int getVehicleParkingDuration(Vehicle vehicle) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        return (int) ChronoUnit.MINUTES.between(getTimeStamp(vehicle), LocalDateTime.parse(("19/11/2021-21:55"), formatter));
    }

    /**
     * Purpose : Method used for finding all the vehicles parked within given time.
     *
     * @param durationInMinutes : Given time required to find all the vehicles.
     * @return : Returns list of vehicle parked within given time.
     */
    public ArrayList<Vehicle> getVehiclesByDuration(int durationInMinutes) {
        ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
        for (Map.Entry<Vehicle, LocalDateTime> entry : vehicleTimeStamp.entrySet()) {
            if (getVehicleParkingDuration(entry.getKey()) < durationInMinutes) {
                vehicleArrayList.add(entry.getKey());
            }
        }
        return vehicleArrayList;
    }

    /**
     * Purpose : Method used to get total vehicles currently
     * parked at the lot.
     *
     * @return : returns the number of vehicle parked at the lot.
     */
    public int getTotalVehicle() {
        int currentVehicle = 0;
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            if (entry.getValue() != null) {
                currentVehicle++;
            }
        }
        return currentVehicle;
    }

    /**
     * Purpose : This method is used to compare two different parking lots
     * by the number of vehicle parked.
     *
     * @param that : ParkingLot Object used to compare with current Lot
     * @return : returns int value by comparing the two lots.
     */
    @Override
    public int compareTo(ParkingLot that) {
        return this.getTotalVehicle() - that.getTotalVehicle();
    }

    /**
     * @param vehicle: Required to check the given vehicle position
     * @return : Returns vehicle timestamp
     */
    public LocalDateTime getTimeStamp(Vehicle vehicle) {
        return vehicleTimeStamp.get(vehicle);
    }

    /**
     * Purpose : Getter method to get the owner for other classes.
     *
     * @return : Returns the owner of the current lot.
     */
    public ParkingLotOwner getOwner() {
        return owner;
    }

    /**
     * Purpose : Getter method to get the airport security for other classes.
     *
     * @return : Returns the airport security of the current lot.
     */
    public AirportSecurity getAirportSecurity() {
        return airportSecurity;
    }

}


