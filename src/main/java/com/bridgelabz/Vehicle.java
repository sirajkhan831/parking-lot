package com.bridgelabz;

/******************************************************************************
 *
 *  Purpose: Vehicle class used to store vehicle's information.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 11-11-2021
 *
 ******************************************************************************/
public class Vehicle {
    private final String vehicleNumber;
    private final String vehicleName;
    private boolean isDriverHandicapped;
    private Size vehicleSize;

    enum Size {LARGE, SMALL}

    /**
     * Purpose: Default Constructor to initialize vehicle name & name.
     *
     * @param vehicleNumber : vehicle number used to store vehicle's unique number
     * @param vehicleName   : vehicle name used for vehicle's name.
     */
    public Vehicle(String vehicleNumber, String vehicleName) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
    }

    /**
     * Purpose: Default Constructor to initialize vehicle name & name.
     *
     * @param vehicleNumber       : vehicle number used to store vehicle's unique number
     * @param vehicleName         : vehicle name used for vehicle's name.
     * @param isDriverHandicapped : Used by handicapped drivers for highest priority in lot.
     */
    public Vehicle(String vehicleNumber, String vehicleName, Size vehicleSize, boolean isDriverHandicapped) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.isDriverHandicapped = isDriverHandicapped;
        this.vehicleSize = vehicleSize;
    }

    public Size getVehicleSize() {
        return vehicleSize;
    }

    public boolean isDriverHandicapped() {
        return isDriverHandicapped;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }
}