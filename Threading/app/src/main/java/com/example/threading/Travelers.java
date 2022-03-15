package com.example.threading;

import java.util.ArrayList;
import java.util.Collection;

public class Travelers {
    private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
    private Object baton = new Object();

    public Passenger getRandomPassenger() {
        synchronized (baton) {
            Passenger passenger = null;
            if (!passengers.isEmpty()) {
                int j = (int) Math.random() * passengers.size();
                passenger = passengers.get(j);
                passengers.remove(j);
            }
            return passenger;
        }
    }

    public void addAll(Collection<Passenger> from) {
        synchronized (baton) {
            passengers.addAll(from);
        }
    }

    public void add(Passenger passenger) {
        synchronized (baton) {
            passengers.add(passenger);
        }
    }
}
