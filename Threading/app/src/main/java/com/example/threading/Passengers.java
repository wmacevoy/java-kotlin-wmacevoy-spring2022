package com.example.threading;

import java.util.Set;
import java.util.TreeSet;

public class Passengers {
    private TreeSet<Passenger> roster = new TreeSet<>();
    private Object baton = new Object();

    public void add(Passenger passenger) {
        synchronized (baton) {
            roster.add(passenger);
        }
    }

    public Set<Passenger> unload() {
        synchronized (baton) {
            Set<Passenger> unloaded = roster;
            roster = new TreeSet<>();
            return unloaded;
        }
    }

    @Override
    public String toString() { return roster.toString(); }

}
