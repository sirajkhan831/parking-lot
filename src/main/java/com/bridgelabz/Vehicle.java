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
public class Vehicle implements Comparable<Vehicle> {
    private final String vehicleNumber;
    private final String vehicleName;
    private boolean isDriverHandicapped;
    private final Size vehicleSize;
    private final String colour;

    enum Size {LARGE, SMALL}

    /**
     * Purpose: Default Constructor to initialize vehicle name & name.
     *
     * @param vehicleNumber : vehicle number used to store vehicle's unique number
     * @param vehicleName   : vehicle name used for vehicle's name.
     */
    public Vehicle(String vehicleNumber, String vehicleName, String colour, Size vehicleSize) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.colour = colour;
        this.vehicleSize = vehicleSize;
    }

    /**
     * Purpose: Default Constructor to initialize vehicle name & name.
     *
     * @param vehicleNumber       : vehicle number used to store vehicle's unique number
     * @param vehicleName         : vehicle name used for vehicle's name.
     * @param vehicleColour       : vehicle vehicleColour used to determine vehicleColour of vehicle.
     * @param isDriverHandicapped : Used by handicapped drivers for highest priority in lot.
     */
    public Vehicle(String vehicleNumber, String vehicleName, String vehicleColour, Size vehicleSize, boolean isDriverHandicapped) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleName = vehicleName;
        this.colour = vehicleColour;
        this.vehicleSize = vehicleSize;
        this.isDriverHandicapped = isDriverHandicapped;
    }

    /**
     * Purpose: Getter method used for sending data
     *          to other classes.
     *
     * @return : Returns size of the vehicle (LARGE/SMALL)
     */
    public Size getVehicleSize() {
        return vehicleSize;
    }

    /**
     * Purpose: Getter method used for sending data
     *          to other classes.
     *
     * @return : Returns true if the driver is handicapped.
     */
    public boolean isDriverHandicapped() {
        return isDriverHandicapped;
    }

    /**
     * Purpose: Getter method used for sending data
     *          to other classes.
     *
     * @return : Returns plate number of the vehicle
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Purpose: Getter method used for sending data
     *          to other classes.
     *
     * @return : Returns name of the vehicle.
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * Purpose: Getter method used for sending data
     *          to other classes.
     *
     * @return : Returns colour of the vehicle
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * Purpose : This method is used for comparing two vehicles WRT name.
     *
     * @param that : Vehicle object for comparison with current vehicle
     * @return : Returns integer by comparing names alphabetically.
     */
    @Override
    public int compareTo(Vehicle that) {
        return this.getVehicleName().compareTo(that.getVehicleName());
    }
}