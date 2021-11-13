package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Vehicle vehicle = null;
    ParkingLotOwner owner = null;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(3);
        owner = new ParkingLotOwner();
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() {
        vehicle = new Vehicle("KA012311", "Innova");
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
        vehicle = new Vehicle("KA012311", "Innova");
        try {
            parkingLot.park(vehicle);
            var isUnParked = parkingLot.unPark(vehicle);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        vehicle = new Vehicle("KA012311", "Innova");
        try {
            parkingLot.park(vehicle);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void givenLotAtMaxCap_whenRequestedByOwner_shouldReturnTrue() {
        parkingLot.registerOwner(owner);
        vehicle = new Vehicle("KA012311", "Innova");
        Vehicle vehicle1 = new Vehicle("MP013344", "800");
        Vehicle vehicle2 = new Vehicle("MP023344", "500");
        Vehicle vehicle3 = new Vehicle("CG043344", "700");
        try {
            parkingLot.park(vehicle);
            parkingLot.park(vehicle1);
            parkingLot.park(vehicle2);
            parkingLot.park(vehicle3);
            Assertions.assertTrue(owner.isAtMaxCapacity());
        } catch (ParkingLotException e) {
            Assertions.assertTrue(owner.isAtMaxCapacity());
            System.out.println(e.getMessage());
        }
    }
}