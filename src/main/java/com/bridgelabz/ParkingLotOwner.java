package com.bridgelabz;

/**
 * @author -> Siraj
 * @version -> 0.1
 * @since -> 12/11/2021
 */
public class ParkingLotOwner {
    private boolean isAtMaxCapacity;

    /**
     * Purpose -> This method is for assigning full parking lot.
     */
    public void capacityFull() {
        isAtMaxCapacity = true;
    }

    /**
     * @return -> Returns true if parking lot is at max capacity
     */
    public boolean isAtMaxCapacity() {
        return isAtMaxCapacity;
    }
}
