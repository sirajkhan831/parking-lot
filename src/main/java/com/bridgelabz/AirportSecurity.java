package com.bridgelabz;

/**
 * @author -> Siraj
 * @version -> 0.1
 * @since -> 12/11/2021
 */
public class AirportSecurity implements Observer {
    private boolean isAtMaxCapacity;

    /**
     * Purpose -> This method is for assigning full parking lot.
     *
     * @param capacityCheck -> Sets lot at max capacity if true.
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
