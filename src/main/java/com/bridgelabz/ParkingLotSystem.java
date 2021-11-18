package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLotSystem {
    private ParkingLot lot1;
    private ParkingLot lot2;
    private ParkingLot lot3;
    private boolean singleLotParking;
    private final ParkingLotOwner owner;
    private final AirportSecurity security;
    private final List<ParkingLot> lots = new ArrayList<>();
    private final HashMap<Vehicle, ParkingLot> vehicleLot = new HashMap<>();

    public ParkingLotSystem(int maxCapacity, ParkingLotOwner owner, AirportSecurity airportSecurity) {
        this.owner = owner;
        this.security = airportSecurity;
        setMaxCapacity(maxCapacity);
        lots.add(lot1);
        lots.add(lot2);
        lots.add(lot3);
    }

    public void setMaxCapacity(int maxCapacity) {
        lot1 = new ParkingLot(maxCapacity, owner, security);
        lot2 = new ParkingLot(maxCapacity, owner, security);
        lot3 = new ParkingLot(maxCapacity, owner, security);
    }

    public void park(Vehicle vehicle, LocalDateTime time) throws ParkingLotException {
        if (vehicleLot.containsKey(vehicle)) {
            throw new ParkingLotException("Vehicle already parked");
        }
        if (!singleLotParking) {
            Collections.sort(lots);
            if (vehicle.getVehicleSize() != Vehicle.Size.LARGE) {
                Collections.reverse(lots);
            }
        }
        lots.get(0).park(vehicle, time);
        vehicleLot.put(vehicle, lots.get(0));
    }

    public void unPark(Vehicle vehicle) throws ParkingLotException {
        getVehicleLot(vehicle).unPark(vehicle);
        vehicleLot.remove(vehicle);
    }

    public ParkingLot getVehicleLot(Vehicle vehicle) {
        return vehicleLot.get(vehicle);
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicleLot.containsKey(vehicle);
    }

    public void singleLotParking() {
        this.singleLotParking = true;
    }

    public HashMap<Vehicle, ParkingLot> getVehicleLot() {
        return vehicleLot;
    }
}