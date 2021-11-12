package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Vehicle vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
        vehicle = new Vehicle(9760);
    }

    @Test
    void checkOne() {
        Assertions.assertTrue(parkingLot.park(vehicle));
    }


    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() {
        parkingLot.park(vehicle);
        boolean isUnParked = parkingLot.unPark(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        parkingLot.park(vehicle);
        boolean isParked = parkingLot.park(vehicle);
        Assertions.assertFalse(isParked);
    }
}