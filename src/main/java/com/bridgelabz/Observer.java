package com.bridgelabz;

/******************************************************************************
 *
 *  Purpose: Observer interface implemented by airport's security & owner.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 13-11-2021
 *
 ******************************************************************************/
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
