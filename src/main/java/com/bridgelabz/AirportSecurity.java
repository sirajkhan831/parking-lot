package com.bridgelabz;

/******************************************************************************
 *
 *  Purpose: Airport Security class used to give security lot's updates.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 13-11-2021
 ******************************************************************************/
public class AirportSecurity implements Observer {
    private boolean isAtMaxCapacity;

    /**
     * Purpose : This method is for assigning full parking lot.
     *
     * @param capacityCheck : Sets lot at max capacity if true.
     */
    @Override
    public void capacityFull(boolean capacityCheck) {
        isAtMaxCapacity = capacityCheck;
    }

    /**
     * @return : Returns true if parking lot is at max capacity
     */
    @Override
    public boolean isAtMaxCapacity() {
        return isAtMaxCapacity;
    }
}
