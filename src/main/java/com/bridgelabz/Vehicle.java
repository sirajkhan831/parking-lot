package com.bridgelabz;

/**
 * @author -> Siraj
 * @version -> 0.1
 * @since -> 10/11/2021
 */
public class Vehicle {
    private final String vehicleNumber;
    private final String vehicleName;
    private boolean isDriverHandicapped;

    public Vehicle(String vehicleNumber, String vehicleName) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
    }

    public Vehicle(String vehicleNumber, String vehicleName, boolean isDriverHandicapped) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.isDriverHandicapped = isDriverHandicapped;
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