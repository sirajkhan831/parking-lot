package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Object vehicle = null;

    @Before
    public void setUp(){
        vehicle = new Object();
        parkingLot = new ParkingLot();
    }

    @Test
    public void givenAVehicle_whenParked_ShouldReturnTrue() {
        boolean isParked = parkingLot.park(new Object());
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_whenUnParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isUnParked = parkingLot.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        parkingLot.park(vehicle);
        parkingLot.unPark(vehicle);
        var isParked = parkingLot.park(vehicle);
        Assert.assertFalse(isParked);
    }
}