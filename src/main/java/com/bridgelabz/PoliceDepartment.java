package com.bridgelabz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private String vehicleName = null;
    private String vehicleColour = null;
    private Vehicle.Size vehicleSize = null;

    /**
     * Purpose : Default constructor to initialize the parameters.
     *
     * @param parkingLotSystem : Used to check for vehicles in given parking lot.
     */
    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    /**
     * Purpose : Iterates vehicle hashmap for other methods.
     *
     * @return : Returns list of vehicle which matches.
     */
    private ArrayList<Vehicle> entrySet() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (Map.Entry<Vehicle, ParkingLot> entry : parkingLotSystem.getVehicleLotMap().entrySet()) {
            if (vehicleName != null && vehicleColour == null) {
                if (vehicleName.equals(entry.getKey().getVehicleName())) {
                    vehicles.add(entry.getKey());
                }
            }
            if (vehicleColour != null && vehicleName == null) {
                if (vehicleColour.equals(entry.getKey().getColour())) {
                    vehicles.add(entry.getKey());
                }
            }
            if (vehicleName != null && vehicleColour != null) {
                if (vehicleName.equals(entry.getKey().getVehicleName()) && vehicleColour.equals(entry.getKey().getColour())) {
                    vehicles.add(entry.getKey());
                }
            }
            if (this.vehicleSize != null && this.vehicleSize.equals(entry.getKey().getVehicleSize())) {
                vehicles.add(entry.getKey());
            }
        }
        Collections.sort(vehicles);
        return vehicles;
    }

    /**
     * Purpose : Used to find the list of vehicles by given colour
     *
     * @param vehicleColour : All vehicles are checked for this colour
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
        return entrySet();
    }

    /**
     * Purpose : Used to find the list of vehicles by given name
     *
     * @param vehicleName : All vehicles are checked for this name
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleByName(String vehicleName) {
        this.vehicleName = vehicleName;
        return entrySet();
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
     * Purpose : Used to find the list of vehicles by given size
     *
     * @param size : All vehicles are checked for this size
     * @return : Returns list of vehicle which matches the given scenario.
     */
    public ArrayList<Vehicle> getVehicleBySize(Vehicle.Size size) {
        this.vehicleSize = size;
        return entrySet();
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

    /**
     * Purpose : Used to find if the given vehicle has fraudulent number plate.
     *
     * @param vehicle : Given Vehicle will be used to check the number
     * @return : Returns false if the number is fraudulent.
     */
    public boolean validateVehicleNumber(Vehicle vehicle) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$");
        Matcher matcher = pattern.matcher(vehicle.getVehicleNumber());
        return matcher.matches();
    }
}
