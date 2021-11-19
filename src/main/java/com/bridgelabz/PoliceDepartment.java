package com.bridgelabz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/******************************************************************************
 *
 *  Purpose: Police Department class used to give lot's security information.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 18-11-2021
 *
 ******************************************************************************/
public class PoliceDepartment {
    private final ParkingLotSystem parkingLotSystem;

    /**
     * Purpose : Default constructor to initialize the parameters.
     *
     * @param parkingLotSystem : Used to check for vehicles in given parking lot.
     */
    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    /**
     * Purpose : Used to find the list of vehicles by given colour
     *
     * @param vehicleColour : All vehicles are checked for this colour
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByColour(String vehicleColour) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        for (Map.Entry<Vehicle, ParkingLot> entry : parkingLotSystem.getVehicleLotMap().entrySet()) {
            if (entry.getKey().getColour().equals(vehicleColour)) {
                vehicleList.add(entry.getKey());
            }
        }
        Collections.sort(vehicleList);
        return vehicleList;
    }

    /**
     * Purpose : Used to find the list of vehicles by given name
     *
     * @param vehicleName : All vehicles are checked for this name
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByName(String vehicleName) {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        for (Map.Entry<Vehicle, ParkingLot> entry : parkingLotSystem.getVehicleLotMap().entrySet()) {
            if (entry.getKey().getVehicleName().equals(vehicleName)) {
                vehicleList.add(entry.getKey());
            }
        }
        Collections.sort(vehicleList);
        return vehicleList;
    }

    /**
     * Purpose : Used to find the list of vehicles by given name & colour
     *
     * @param vehicleName : All vehicles are checked for this name & colour
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByNameAndColour(String vehicleName, String vehicleColour) {
        ArrayList<Vehicle> vehiclesByName = getVehicleByName(vehicleName);
        ArrayList<Vehicle> vehiclesByColour = getVehicleByColour(vehicleColour);
        vehiclesByName.retainAll(vehiclesByColour);
        return vehiclesByName;
    }

    /**
     * Purpose : Used to find the list of vehicles parked within given duration
     *
     * @param durationInMinutes : Duration required to check for vehicles parked within this time.
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByDuration(int durationInMinutes) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (ParkingLot lot : parkingLotSystem.getLotList()) {
            vehicles.addAll(lot.getVehiclesByDuration(durationInMinutes));
        }
        Collections.sort(vehicles);
        return vehicles;
    }
}
