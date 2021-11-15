package com.bridgelabz;

/******************************************************************************
 *
 *  Purpose: Parking lot owner class used to give owner's lot updates.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 13-11-2021
 *
 ******************************************************************************/
public class ParkingLotOwner implements Observer {
    private boolean isAtMaxCapacity;

    /**
     * Purpose -> This method is for assigning full parking lot.
     *
     * @param capacityCheck ->
     */
    @Override
    public void capacityFull(boolean capacityCheck) {
        isAtMaxCapacity = capacityCheck;
    }

    /**
     * @return -> Returns true if parking lot is at max capacity
     */
    @Override
    public boolean isAtMaxCapacity() {
        return isAtMaxCapacity;
    }
}
