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
        vehicle = null;
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            boolean isParked = parkingLot.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }


    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() {
        try {
            parkingLot.park(vehicle);
            boolean isUnParked = parkingLot.unPark(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking failed", e.getMessage());
            e.printStackTrace();
        }
    }
}