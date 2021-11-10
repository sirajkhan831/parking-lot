package com.bridgelabz;

import java.util.Objects;

/**
 * @author -> Siraj
 * @version -> 0.1
 * @since -> 10/11/2021
 */
public class Vehicle {
    private final int number;

    public Vehicle(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return number == vehicle.number;
    }

    public int getNumber() {
        return number;
    }
}
