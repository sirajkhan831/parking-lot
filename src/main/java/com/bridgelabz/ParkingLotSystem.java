package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.*;

/******************************************************************************
 *
 *  Purpose: Parking lot system which ensures parking lot for each vehicle.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 17-11-2021
 *
 ******************************************************************************/

public class ParkingLotSystem {
    private ParkingLot A;
    private ParkingLot B;
    private ParkingLot C;
    private ParkingLot D;
    private boolean singleLotParking = false;
    private final ParkingLotOwner owner;
    private final AirportSecurity security;
    private final ArrayList<ParkingLot> parkingLots = new ArrayList<>();
    private final HashMap<Vehicle, ParkingLot> vehicleParkingLotHashMap = new HashMap<>();

    /**
     * Purpose: Police Department class used to give lot's security information.
     *
     * @param maxCapacity     : Parameter for setting the maximum capacity of lots.
     * @param owner           : Used for setting the owner of the lot.
     * @param airportSecurity : Used for setting the airport security of the lot.
     */
    public ParkingLotSystem(int maxCapacity, ParkingLotOwner owner, AirportSecurity airportSecurity) {
        this.owner = owner;
        this.security = airportSecurity;
        setMaxCapacity(maxCapacity);
        parkingLots.add(A);
        parkingLots.add(B);
        parkingLots.add(C);
        parkingLots.add(D);
    }

    /**
     * Purpose: Method for setting the maximum capacity of lots.
     *
     * @param maxCapacity : Parameter for setting the maximum capacity of lots.
     */
    public void setMaxCapacity(int maxCapacity) {
        A = new ParkingLot(maxCapacity, owner, security);
        B = new ParkingLot(maxCapacity, owner, security);
        C = new ParkingLot(maxCapacity, owner, security);
        D = new ParkingLot(maxCapacity, owner, security);
    }

    /**
     * @param vehicle : Takes Vehicle object and parks the given vehicle.
     * @param time    : Takes LocalTime and sets the parking time of vehicle.
     * @throws ParkingLotException : Custom exception for invalid parking.
     */
    public void park(Vehicle vehicle, LocalDateTime time) throws ParkingLotException {
        if (vehicleParkingLotHashMap.containsKey(vehicle)) {
            throw new ParkingLotException("Vehicle already parked");
        }
        if (!singleLotParking) {
            Collections.sort(parkingLots);
            Collections.reverse(parkingLots);
        }
        if (vehicle.getVehicleSize() == Vehicle.Size.LARGE) {
            Collections.reverse(parkingLots);
        }
        parkingLots.get(0).park(vehicle, time);
        vehicleParkingLotHashMap.put(vehicle, parkingLots.get(0));
    }

    /**
     * Purpose: Used to un-parks an already parked vehicle
     *
     * @param vehicle: Takes Vehicle object and un-parks the given vehicle.
     */
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        getLot(vehicle).unPark(vehicle);
        vehicleParkingLotHashMap.remove(vehicle);
    }

    /**
     * Purpose : Used to get the vehicle's parking lot.
     *
     * @param vehicle : Takes Vehicle object and finds its lot.
     * @return : Lot of the given vehicle
     */
    public ParkingLot getLot(Vehicle vehicle) {
        return vehicleParkingLotHashMap.get(vehicle);
    }

    /**
     * Purpose : Used to find if the given vehicle is parked.
     *
     * @param vehicle : Checks the given vehicle's parking status.
     * @return : Returns true if the given vehicle is parked
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicleParkingLotHashMap.containsKey(vehicle);
    }

    /**
     * Purpose : This method ensures that all vehicles are parked
     * at single parking lot.
     *
     * @param singleLotParking : Boolean Value if assign single lot.
     */
    public void singleLotParking(boolean singleLotParking) {
        this.singleLotParking = singleLotParking;
    }

    /**
     * Purpose : Getter method used in other classes.
     *
     * @return : Returns HashMap of Vehicle and it's lot
     */
    public HashMap<Vehicle, ParkingLot> getVehicleLotMap() {
        return vehicleParkingLotHashMap;
    }

    /**
     * Purpose : Getter method used in other classes.
     *
     * @return : Returns parkingLot array.
     */
    public ArrayList<ParkingLot> getLotList() {
        return parkingLots;
    }
}