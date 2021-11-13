package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    ParkingLotOwner owner = null;
    Vehicle innova = null;
    AirportSecurity airportSecurity = null;

    @BeforeEach
    void setUp() {
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        innova = new Vehicle("KA012311", "Innova");
        parkingLot = new ParkingLot(10, owner, airportSecurity);
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() {
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
        try {
            parkingLot.park(innova);
            parkingLot.unPark(innova);
            Assertions.assertFalse(parkingLot.isVehicleParked(innova));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLot.park(innova);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLot.park(innova));
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() {
        parkingLot.registerOwner(owner);
        parkingLot.setMaxCapacity(4);
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
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() {
        parkingLot.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG043344", "Santro");
        Vehicle swift = new Vehicle("MP013344", "Swift");
        try {
            parkingLot.park(swift);
            parkingLot.park(santro);
            Assertions.assertTrue(parkingLot.isVehicleParked(santro) && parkingLot.isVehicleParked(swift));
        } catch (ParkingLotException e) {
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() {
        parkingLot.registerAirportSecurity(airportSecurity);
        parkingLot.setMaxCapacity(4);
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
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }
}