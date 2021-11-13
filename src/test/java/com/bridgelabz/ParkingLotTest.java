package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Vehicle innova = null;
    ParkingLotOwner owner = null;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(3);
        owner = new ParkingLotOwner();
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() {
        innova = new Vehicle("KA012311", "Innova");
        try {
            parkingLot.park(innova);
            boolean isParked = parkingLot.isVehicleParked(innova);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() {
        innova = new Vehicle("KA012311", "Innova");
        try {
            parkingLot.park(innova);
            var isUnParked = parkingLot.unPark(innova);
            Assertions.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        innova = new Vehicle("KA012311", "Innova");
        try {
            parkingLot.park(innova);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.park(innova));
    }

    @Test
    void givenLotAtMaxCap_whenRequestedByOwner_shouldReturnTrue() {
        parkingLot.registerOwner(owner);
        innova = new Vehicle("KA012311", "Innova");
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        try {
            parkingLot.park(innova);
            parkingLot.park(swift);
            parkingLot.park(alto);
            parkingLot.park(santro);
            Assertions.assertTrue(owner.isAtMaxCapacity());
        } catch (ParkingLotException e) {
            Assertions.assertTrue(owner.isAtMaxCapacity());
            System.out.println(e.getMessage());
        }
    }
}