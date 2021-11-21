package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotTestCases {
    ParkingLotOwner owner = null;
    Vehicle bmw = null;
    AirportSecurity airportSecurity = null;
    DateTimeFormatter formatter;
    ParkingLotSystem parkingSystem = null;
    PoliceDepartment police = null;

    @BeforeEach
    void setUp() {
        owner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        bmw = new Vehicle("KA012311", "bmw", "WHITE", Vehicle.Size.SMALL);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        parkingSystem = new ParkingLotSystem(4, owner, airportSecurity);
        police = new PoliceDepartment(parkingSystem);
        parkingSystem.singleLotParking(false);
    }

    @Test
    void givenAVehicle_ifParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-14:50", formatter));
        boolean isParked = parkingSystem.isVehicleParked(bmw);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_whenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.unPark(bmw);
        Assertions.assertFalse(parkingSystem.isVehicleParked(bmw));
    }

    @Test
    void givenAVehicle_whenAlreadyParked_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
            parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        });
    }

    @Test
    void givenCapacityIsFull_ShouldInformOwner() throws ParkingLotException {
        parkingSystem.singleLotParking(true);
        Vehicle toyota = new Vehicle("MP01AS3344", "toyota", "BLUE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02WQ3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        Vehicle santro = new Vehicle("CG04HG3344", "Santro", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-19:49", formatter));
        parkingSystem.park(toyota, LocalDateTime.parse("17/11/2021-03:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-13:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-16:49", formatter));
        Assertions.assertTrue(parkingSystem.getLot(bmw).getOwner().isAtMaxCapacity());
    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicle() throws ParkingLotException {
        parkingSystem.setMaxCapacity(2);
        Vehicle santro = new Vehicle("CG04AS3344", "Santro", "WHITE", Vehicle.Size.SMALL);
        Vehicle swift = new Vehicle("MP01EW3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-19:39", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-12:42", formatter));
        Assertions.assertTrue(parkingSystem.isVehicleParked(santro) && parkingSystem.isVehicleParked(swift));
    }

    @Test
    void givenCapacityIsFull_ShouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.singleLotParking(true);
        Vehicle swift = new Vehicle("MP01QQ3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02GG3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        Vehicle santro = new Vehicle("CG04HO3344", "Santro", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(parkingSystem.getLot(bmw).getAirportSecurity().isAtMaxCapacity());
    }

    @Test
    void givenWhenParkingLotHasSpace_ShouldInformOwner() throws ParkingLotException {
        parkingSystem.setMaxCapacity(4);
        parkingSystem.singleLotParking(true);
        Vehicle swift = new Vehicle("MP01BN3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02CX3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        Vehicle santro = new Vehicle("CG04ZX3344", "Santro", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertTrue(parkingSystem.getLot(bmw).getOwner().isAtMaxCapacity());
        parkingSystem.unPark(alto);
        Assertions.assertFalse(parkingSystem.getLot(bmw).getOwner().isAtMaxCapacity());
    }

    @Test
    void givenAVehicle_whenParked_ShouldReturnParkingPosition() throws ParkingLotException {
        parkingSystem.singleLotParking(true);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        Vehicle santro = new Vehicle("CG04SS3344", "Santro", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(swift, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.unPark((bmw));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-09:49", formatter));
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-09:49", formatter));
        Assertions.assertEquals(3, parkingSystem.getLot(swift).getVehiclePosition(swift));
        Assertions.assertEquals(1, parkingSystem.getLot(alto).getVehiclePosition(alto));
    }

    @Test
    void givenAVehicle_ifRequired_ShouldReturnTimeStamp() throws ParkingLotException {
        parkingSystem.park(bmw, LocalDateTime.now());
        Assertions.assertEquals(LocalDateTime.now().format(formatter), parkingSystem.getLot(bmw).getTimeStamp(bmw).format(formatter));
    }

    @Test
    void givenAHandicappedDriver_ifPossible_ShouldBeGivenFirstSlot() throws ParkingLotException {
        Vehicle santro = new Vehicle("CG06A9722", "Santro", "WHITE", Vehicle.Size.SMALL, true);
        parkingSystem.park(bmw, LocalDateTime.parse("17/11/2021-17:49", formatter));
        parkingSystem.park(santro, LocalDateTime.parse("17/11/2021-12:49", formatter));
        Assertions.assertEquals(3, parkingSystem.getLot(bmw).getVehiclePosition(bmw));
        Assertions.assertEquals(0, parkingSystem.getLot(santro).getVehiclePosition(santro));
    }

    @Test
    void givenVehicleColour_ifRequiredVehicle_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(toyota, LocalDateTime.parse("18/11/2021-22:18", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("18/11/2021-21:18", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("18/11/2021-22:38", formatter));
        Assertions.assertEquals(alto, police.getVehicleByColour("WHITE").get(0));
    }

    @Test
    void givenVehicleName_ifRequiredVehicle_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(toyota, LocalDateTime.parse("18/11/2021-22:18", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("18/11/2021-21:18", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("18/11/2021-22:38", formatter));
        Assertions.assertEquals(List.of(toyota), police.getVehicleByName("toyota"));
    }

    @Test
    void givenVehicleNameAndColour_ifRequiredVehicle_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swiftWhite = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle swiftBlue = new Vehicle("MP02PO3344", "Swift", "BLUE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(toyota, LocalDateTime.parse("18/11/2021-22:18", formatter));
        parkingSystem.park(swiftWhite, LocalDateTime.parse("18/11/2021-21:18", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("18/11/2021-22:38", formatter));
        parkingSystem.park(swiftBlue, LocalDateTime.parse("18/11/2021-22:18", formatter));
        Assertions.assertEquals(List.of(swiftWhite), police.getVehicleByNameAndColour("Swift", "WHITE"));
    }

    @Test
    void givenVehicle_ifRequiredParkingDuration_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(toyota, LocalDateTime.parse("19/11/2021-21:18", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("18/11/2021-21:18", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("18/11/2021-22:38", formatter));
        Assertions.assertEquals(37, parkingSystem.getLot(toyota).getVehicleParkingDuration(toyota));
    }

    @Test
    void givenDuration_ifRequiredParkedVehicle_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL);
        parkingSystem.park(toyota, LocalDateTime.parse("19/11/2021-21:55", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("19/11/2021-21:01", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("19/11/2021-21:01", formatter));
        Assertions.assertEquals(List.of(toyota), police.getVehicleByDuration(30));
    }

    @Test
    void givenVehicleSizeAndDriver_ifRequiredListOfVehicle_ShouldReturnEqual() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("MP01PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL, true);
        parkingSystem.park(toyota, LocalDateTime.parse("19/11/2021-21:55", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("19/11/2021-21:01", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("19/11/2021-21:01", formatter));
        ArrayList<Vehicle> vehicles = police.getVehicleBySize(Vehicle.Size.SMALL);
        vehicles.removeIf(vehicle -> !vehicle.isDriverHandicapped());
        Assertions.assertEquals(List.of(alto), vehicles);
    }

    @Test
    void givenVehicleNumber_ifFraudulent_ShouldReturnFalse() throws ParkingLotException {
        Vehicle toyota = new Vehicle("DL12RE3387", "toyota", "BLUE", Vehicle.Size.LARGE);
        Vehicle swift = new Vehicle("M201PO3344", "Swift", "WHITE", Vehicle.Size.SMALL);
        Vehicle alto = new Vehicle("MP02JK3344", "Alto", "WHITE", Vehicle.Size.SMALL, true);
        parkingSystem.park(toyota, LocalDateTime.parse("19/11/2021-21:55", formatter));
        parkingSystem.park(swift, LocalDateTime.parse("19/11/2021-21:01", formatter));
        parkingSystem.park(alto, LocalDateTime.parse("19/11/2021-21:01", formatter));
        Assertions.assertFalse(police.validateVehicleNumber(swift));
    }
}