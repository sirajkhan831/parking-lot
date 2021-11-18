package com.bridgelabz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private ParkingLotOwner owner;
    private AirportSecurity airportSecurity;
    private final HashMap<Integer, Vehicle> vehicles = new HashMap<>();
    private final HashMap<Integer, String> vehicleTimeStamp = new HashMap<>();

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
     */
    public void park(Vehicle vehicle, LocalDateTime time) throws ParkingLotException {
        if (isAtMaxCapacity()) {
            throw new ParkingLotException("Parking already full");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        String timeStamp = formatter.format(time);
        if (isVehicleParked(vehicle)) {
            throw new ParkingLotException("Vehicle already parked");
        }
        if (vehicle.isDriverHandicapped()) {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i) == null) {
                    vehicles.put(i, vehicle);
                    vehicleTimeStamp.put(i, timeStamp);
                    break;
                }
            }
        } else {
            for (int i = vehicles.size() - 1; i >= 0; i--) {
                if (vehicles.get(i) == null) {
                    vehicles.put(i, vehicle);
                    vehicleTimeStamp.put(i, timeStamp);
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
            vehicleTimeStamp.put(index, null);
            index++;
        }
    }

    /**
     * Purpose: Used registers the owner for the parking lot
     *
     * @param owner: Required to set the owner
     */
    public void registerObserver(ParkingLotOwner owner) {
        this.owner = owner;
    }

    /**
     * Purpose: Used to registers the airport security for the parking lot
     *
     * @param airportSecurity: Required to set the airport security
     */
    public void registerObserver(AirportSecurity airportSecurity) {
        this.airportSecurity = airportSecurity;
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
     * @param vehicle: Required to check the given vehicle position
     * @return : Returns vehicle timestamp
     */
    public String getTimeStamp(Vehicle vehicle) {
        return vehicleTimeStamp.get(getVehiclePosition(vehicle));
    }

    public int getTotalVehicle() {
        int currentVehicle = 0;
        for (Map.Entry<Integer, Vehicle> entry : vehicles.entrySet()) {
            if (entry.getValue() != null) {
                currentVehicle++;
            }
        }
        return currentVehicle;
    }

    @Override
    public int compareTo(ParkingLot that) {
        return this.getTotalVehicle() - that.getTotalVehicle();
    }

    public ParkingLotOwner getOwner() {
        return owner;
    }

    public AirportSecurity getAirportSecurity() {
        return airportSecurity;
    }
}


