package com.bridgelabz;

/**********************************************************************************
 *
 *  Purpose: Parking lot exception used to throw exception during invalid parking.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 13-11-2021
 *
 **********************************************************************************/
public class ParkingLotException extends Exception {
    public ParkingLotException(String message) {
        super(message);
    }
}
