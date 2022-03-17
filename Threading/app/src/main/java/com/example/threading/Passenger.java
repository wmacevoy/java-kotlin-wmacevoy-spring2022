package com.example.threading;

import java.util.Objects;

public class Passenger implements Comparable<Passenger> {
    final String name;
    Passenger(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return name.equals(passenger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Passenger passenger) {
        return name.compareTo(passenger.name);
    }

    @Override
    public String toString() { return name ; }

}
