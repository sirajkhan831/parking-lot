package com.bridgelabz;

public interface Observer {

    /**
     * Purpose -> This method is for assigning full parking lot.
     */
    void capacityFull(boolean capacityCheck);

    /**
     * @return -> Returns true if parking lot is at max capacity
     */
    boolean isAtMaxCapacity();
}
