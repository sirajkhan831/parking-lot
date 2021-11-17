package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    ParkingLotOwner owner = null;
    Vehicle innova = null;
    AirportSecurity airportSecurity = null;
    DateTimeFormatter formatter;

    @BeforeEach
    void setUp() {
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        innova = new Vehicle("KA012311", "Innova");
        parkingLot = new ParkingLot(4, owner, airportSecurity);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-14:50"));
        boolean isParked = parkingLot.isVehicleParked(innova);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.unPark(innova);
        Assertions.assertFalse(parkingLot.isVehicleParked(innova));
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
            parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        });
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() throws ParkingLotException {
        parkingLot.setMaxCapacity(4);
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP01AS3344", "Swift");
        Vehicle alto = new Vehicle("MP02WQ3344", "Alto");
        Vehicle santro = new Vehicle("CG04HG3344", "Santro");
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-19:49", formatter));
        parkingLot.park(swift, LocalDateTime.parse("17/11/2021-03:49", formatter));
        parkingLot.park(alto, LocalDateTime.parse("17/11/2021-13:49", formatter));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-16:49", formatter));
        Assertions.assertTrue(owner.isAtMaxCapacity());
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() throws ParkingLotException {
        parkingLot.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG04AS3344", "Santro");
        Vehicle swift = new Vehicle("MP01EW3344", "Swift");
        parkingLot.park(swift, LocalDateTime.parse("17/11/2021-19:39", formatter));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-12:42", formatter));
        Assertions.assertTrue(parkingLot.isVehicleParked(santro) && parkingLot.isVehicleParked(swift));
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        parkingLot.registerObserver(airportSecurity);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP01QQ3344", "Swift");
        Vehicle alto = new Vehicle("MP02GG3344", "Alto");
        Vehicle santro = new Vehicle("CG04HO3344", "Santro");
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(owner.isAtMaxCapacity());
    }

    @Test
    void givenWhenParkingLotHasSpace_ShouldInformOwner() throws ParkingLotException {
        parkingLot.registerObserver(owner);
        parkingLot.setMaxCapacity(4);
        Vehicle swift = new Vehicle("MP01BN3344", "Swift");
        Vehicle alto = new Vehicle("MP02CX3344", "Alto");
        Vehicle santro = new Vehicle("CG04ZX3344", "Santro");
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(owner.isAtMaxCapacity());
        parkingLot.unPark(alto);
        Assertions.assertFalse(owner.isAtMaxCapacity());
    }

    @Test
    void givenAVehicle_whenParked_ShouldReturnParkingPosition() throws ParkingLotException {
        Vehicle swift = new Vehicle("MP01PO3344", "Swift");
        Vehicle alto = new Vehicle("MP02JK3344", "Alto");
        Vehicle santro = new Vehicle("CG04SS3344", "Santro");
        parkingLot.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.unPark((innova));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertEquals(3, parkingLot.getVehiclePosition(swift));
        Assertions.assertEquals(1, parkingLot.getVehiclePosition(alto));
    }

    @Test
    void givenAVehicle_ifRequired_ShouldReturnTimeStamp() throws ParkingLotException {
        parkingLot.park(innova, LocalDateTime.now());
        Assertions.assertEquals(LocalDateTime.now().format(formatter), parkingLot.getTimeStamp(innova));
    }

    @Test
    void givenAHandicappedDriver_ifPossible_ShouldBeGivenFirstSlot() throws ParkingLotException {
        Vehicle santro = new Vehicle("CG06A9722", "Santro", true);
        parkingLot.park(innova, LocalDateTime.parse("17/11/2021-17:49", formatter));
        parkingLot.park(santro, LocalDateTime.parse("17/11/2021-12:49", formatter));
        Assertions.assertEquals(3, parkingLot.getVehiclePosition(innova));
        Assertions.assertEquals(0, parkingLot.getVehiclePosition(santro));
    }
}