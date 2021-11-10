package com.bridgelabz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Vehicle vehicle = null;

    @Before
    public void setUp(){
        vehicle = new Vehicle(9760);
        parkingLot = new ParkingLot();
    }

    @Test
    public void givenAVehicle_whenParked_ShouldReturnTrue() {
        boolean isParked = parkingLot.park(new Vehicle(8875));
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
        boolean isParked = parkingLot.park(vehicle);
        Assert.assertFalse(isParked);
    }
}