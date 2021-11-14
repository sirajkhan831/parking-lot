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
        parkingLot = new ParkingLot(4, owner, airportSecurity);
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() {
        try {
            parkingLot.park(innova, "09:10");
            boolean isParked = parkingLot.isVehicleParked(innova);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assertions.assertTrue(parkingLot.isVehicleParked(innova));
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() {
        try {
            parkingLot.park(innova, "09:10");
            parkingLot.unPark(innova);
            Assertions.assertFalse(parkingLot.isVehicleParked(innova));
        } catch (ParkingLotException e) {
            Assertions.assertFalse(parkingLot.isVehicleParked(innova));
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(innova, "09:10");
            parkingLot.park(innova, "09:10");
        });
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() {
        parkingLot.setMaxCapacity(4);
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        try {
            parkingLot.park(innova, "09:10");
            parkingLot.park(swift, "09:10");
            parkingLot.park(alto, "09:10");
            parkingLot.park(santro, "09:10");
            Assertions.assertTrue(owner.isAtMaxCapacity());
        } catch (ParkingLotException e) {
            Assertions.assertTrue(owner.isAtMaxCapacity());
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() {
        parkingLot.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG043344", "Santro");
        Vehicle swift = new Vehicle("MP013344", "Swift");
        try {
            parkingLot.park(swift, "09:10");
            parkingLot.park(santro, "09:10");
            Assertions.assertTrue(parkingLot.isVehicleParked(santro) && parkingLot.isVehicleParked(swift));
        } catch (ParkingLotException e) {
            Assertions.assertTrue(parkingLot.isVehicleParked(santro) && parkingLot.isVehicleParked(swift));
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() {
        parkingLot.registerObserver(airportSecurity);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        try {
            parkingLot.park(innova, "09:10");
            parkingLot.park(swift, "09:10");
            parkingLot.park(alto, "09:10");
            parkingLot.park(santro, "09:10");
            Assertions.assertTrue(owner.isAtMaxCapacity());
        } catch (ParkingLotException e) {
            Assertions.assertTrue(owner.isAtMaxCapacity());
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenWhenParkingLotHasSpace_ShouldInformOwner() {
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        try {
            parkingLot.park(innova, "09:10");
            parkingLot.park(swift, "09:10");
            parkingLot.park(alto, "09:10");
            parkingLot.park(santro, "09:10");
            Assertions.assertTrue(owner.isAtMaxCapacity());
            parkingLot.unPark(alto);
            Assertions.assertFalse(owner.isAtMaxCapacity());
        } catch (ParkingLotException e) {
            Assertions.assertFalse(owner.isAtMaxCapacity());
            Assertions.assertEquals("Parking lot is full", e.getMessage());
        }
    }

    @Test
    void givenAVehicle_whenParked_ShouldReturnParkingPosition() {
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        try {
            parkingLot.park(swift, "09:10");
            parkingLot.park(innova, "09:10");
            parkingLot.park(alto, "09:10");
            parkingLot.unPark((innova));
            parkingLot.park(santro, "09:10");
            parkingLot.park(innova, "09:10");
            Assertions.assertEquals(1, parkingLot.getVehiclePosition(swift));
            Assertions.assertEquals(3, parkingLot.getVehiclePosition(alto));
        } catch (ParkingLotException e) {
            Assertions.assertEquals(4, parkingLot.getVehiclePosition(innova));
            Assertions.assertEquals(2, parkingLot.getVehiclePosition(santro));
        }
    }

    @Test
    void givenAVehicle_ifRequired_ShouldReturnTimeStamp() {
        try {
            parkingLot.park(innova, "09:10");
            Assertions.assertEquals("09:10", parkingLot.getTimeStamp(innova));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}