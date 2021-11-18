package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotTest {
    ParkingLotOwner owner = null;
    Vehicle innova = null;
    AirportSecurity airportSecurity = null;
    DateTimeFormatter formatter;
    ParkingLotSystem parkingSystem = null;

    @BeforeEach
    void setUp() {
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        innova = new Vehicle("KA012311", "Innova");
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        parkingSystem = new ParkingLotSystem(4, owner, airportSecurity);
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-14:50", formatter));
        boolean isParked = parkingSystem.isVehicleParked(innova);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.unPark(innova);
        Assertions.assertFalse(parkingSystem.isVehicleParked(innova));
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
            parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        });
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() throws ParkingLotException {
        parkingSystem.singleLotParking();
        Vehicle swift = new Vehicle("MP01AS3344", "Swift");
        Vehicle alto = new Vehicle("MP02WQ3344", "Alto");
        Vehicle santro = new Vehicle("CG04HG3344", "Santro");
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-19:49", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-03:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-13:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-16:49", formatter));
        Assertions.assertTrue(parkingSystem.getVehicleLot(innova).getOwner().isAtMaxCapacity());
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() throws ParkingLotException {
        parkingSystem.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG04AS3344", "Santro");
        Vehicle swift = new Vehicle("MP01EW3344", "Swift");
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-19:39", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-12:42", formatter));
        Assertions.assertTrue(parkingSystem.isVehicleParked(santro) && parkingSystem.isVehicleParked(swift));
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.singleLotParking();
        Vehicle swift = new Vehicle("MP01QQ3344", "Swift");
        Vehicle alto = new Vehicle("MP02GG3344", "Alto");
        Vehicle santro = new Vehicle("CG04HO3344", "Santro");
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(parkingSystem.getVehicleLot(innova).getAirportSecurity().isAtMaxCapacity());
    }

    @Test
    void givenWhenParkingLotHasSpace_ShouldInformOwner() throws ParkingLotException {
        parkingSystem.setMaxCapacity(4);
        parkingSystem.singleLotParking();
        Vehicle swift = new Vehicle("MP01BN3344", "Swift");
        Vehicle alto = new Vehicle("MP02CX3344", "Alto");
        Vehicle santro = new Vehicle("CG04ZX3344", "Santro");
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(parkingSystem.getVehicleLot(innova).getOwner().isAtMaxCapacity());
        parkingSystem.unPark(alto);
        Assertions.assertFalse(parkingSystem.getVehicleLot(innova).getOwner().isAtMaxCapacity());
    }

    @Test
    void givenAVehicle_whenParked_ShouldReturnParkingPosition() throws ParkingLotException {
        Vehicle swift = new Vehicle("MP01PO3344", "Swift");
        Vehicle alto = new Vehicle("MP02JK3344", "Alto");
        Vehicle santro = new Vehicle("CG04SS3344", "Santro");
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.unPark((innova));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertEquals(3, parkingSystem.getVehicleLot(swift).getVehiclePosition(swift));
        Assertions.assertEquals(3, parkingSystem.getVehicleLot(alto).getVehiclePosition(alto));
    }

    @Test
    void givenAVehicle_ifRequired_ShouldReturnTimeStamp() throws ParkingLotException {
        parkingSystem.park(innova, LocalDateTime.now());
        Assertions.assertEquals(LocalDateTime.now().format(formatter), parkingSystem.getVehicleLot(innova).getTimeStamp(innova));
    }

    @Test
    void givenAHandicappedDriver_ifPossible_ShouldBeGivenFirstSlot() throws ParkingLotException {
        Vehicle santro = new Vehicle("CG06A9722", "Santro", Vehicle.Size.LARGE, true);
        parkingSystem.park(innova, LocalDateTime.parse("17/11/2021-17:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-12:49", formatter));
        Assertions.assertEquals(3, parkingSystem.getVehicleLot(innova).getVehiclePosition(innova));
        Assertions.assertEquals(0, parkingSystem.getVehicleLot(santro).getVehiclePosition(santro));
    }
}