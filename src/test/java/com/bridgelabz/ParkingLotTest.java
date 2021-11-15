package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

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
    void givenAVehicle_ifParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.park(innova, LocalTime.parse("09:18"));
        boolean isParked = parkingLot.isVehicleParked(innova);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.park(innova, LocalTime.parse("09:18"));
        parkingLot.unPark(innova);
        Assertions.assertFalse(parkingLot.isVehicleParked(innova));
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(innova, LocalTime.parse("19:18"));
            parkingLot.park(innova, LocalTime.parse("04:18"));
        });
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() throws ParkingLotException {
        parkingLot.setMaxCapacity(4);
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        parkingLot.park(innova, LocalTime.parse("19:18"));
        parkingLot.park(swift, LocalTime.parse("21:18"));
        parkingLot.park(alto, LocalTime.parse("03:18"));
        parkingLot.park(santro, LocalTime.parse("04:18"));
        Assertions.assertTrue(owner.isAtMaxCapacity());
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() throws ParkingLotException {
        parkingLot.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG043344", "Santro");
        Vehicle swift = new Vehicle("MP013344", "Swift");
        parkingLot.park(swift, LocalTime.parse("05:18"));
        parkingLot.park(santro, LocalTime.parse("09:48"));
        Assertions.assertTrue(parkingLot.isVehicleParked(santro) && parkingLot.isVehicleParked(swift));
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        parkingLot.registerObserver(airportSecurity);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        parkingLot.park(innova, LocalTime.parse("09:18"));
        parkingLot.park(swift, LocalTime.parse("09:18"));
        parkingLot.park(alto, LocalTime.parse("09:18"));
        parkingLot.park(santro, LocalTime.parse("09:18"));
        Assertions.assertTrue(owner.isAtMaxCapacity());
    }

    @Test
    void givenWhenParkingLotHasSpace_ShouldInformOwner() throws ParkingLotException {
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        parkingLot.park(innova, LocalTime.parse("09:18"));
        parkingLot.park(swift, LocalTime.parse("09:18"));
        parkingLot.park(alto, LocalTime.parse("09:18"));
        parkingLot.park(santro, LocalTime.parse("09:18"));
        Assertions.assertTrue(owner.isAtMaxCapacity());
        parkingLot.unPark(alto);
        Assertions.assertFalse(owner.isAtMaxCapacity());
    }

    @Test
    void givenAVehicle_whenParked_ShouldReturnParkingPosition() throws ParkingLotException {
        Vehicle swift = new Vehicle("MP013344", "Swift");
        Vehicle alto = new Vehicle("MP023344", "Alto");
        Vehicle santro = new Vehicle("CG043344", "Santro");
        parkingLot.park(swift, LocalTime.parse("09:18"));
        parkingLot.park(innova, LocalTime.parse("09:18"));
        parkingLot.park(alto, LocalTime.parse("09:18"));
        parkingLot.unPark((innova));
        parkingLot.park(santro, LocalTime.parse("09:18"));
        parkingLot.park(innova, LocalTime.parse("09:18"));
        Assertions.assertEquals(3, parkingLot.getVehiclePosition(swift));
        Assertions.assertEquals(1, parkingLot.getVehiclePosition(alto));
    }

    @Test
    void givenAVehicle_ifRequired_ShouldReturnTimeStamp() throws ParkingLotException {
        parkingLot.park(innova, LocalTime.parse("22:32"));
        Assertions.assertEquals("22:32", parkingLot.getTimeStamp(innova));
    }

    @Test
    void givenAHandicappedDriver_ifPossible_ShouldBeGivenFirstSlot() throws ParkingLotException {
        Vehicle santro = new Vehicle("CG06A9722", "Santro", true);
        parkingLot.park(innova, LocalTime.parse("09:18"));
        parkingLot.park(santro, LocalTime.parse("09:18"));
        Assertions.assertEquals(3, parkingLot.getVehiclePosition(innova));
        Assertions.assertEquals(0, parkingLot.getVehiclePosition(santro));
    }
}