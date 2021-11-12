package com.bridgelabz;

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

    /**
     * @param vehicle -> Required to park the given vehicle.
     */
    public void park(Vehicle vehicle) throws ParkingLotException {
        if (this.vehicle != null) {
            throw new ParkingLotException("Parking failed");
        }
        this.vehicle = vehicle;
    }

    /**
     * @param vehicle -> Required to un-park the given vehicle.
     * @return -> Returns boolean by checking if the given vehicle exists.
     */
    public boolean unPark(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return this.vehicle == vehicle;
    }
}
