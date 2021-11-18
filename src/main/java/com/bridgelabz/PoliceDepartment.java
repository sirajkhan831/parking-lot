package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/******************************************************************************
 *
 *  Purpose: Police Department class used to give lot's security information.
 *
 *  @author Siraj
 *  @version 1.0
 *  @since 18-11-2021
 *
 ******************************************************************************/
public class PoliceDepartment {
    private final ParkingLotSystem parkingLotSystem;

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public List<Vehicle> getVehicleByColour(String colour) {
        List<Vehicle> vehicleList = new ArrayList<>();
        for (Map.Entry<Vehicle, ParkingLot> entry : parkingLotSystem.getVehicleLot().entrySet()) {
            if (entry.getKey().getColour().equals(colour)) {
                vehicleList.add(entry.getKey());
            }
        }
        return vehicleList;
    }
}
