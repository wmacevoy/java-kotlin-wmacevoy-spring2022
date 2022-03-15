package com.example.threading;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Airplane implements Runnable {
    String name;
    private Set<Passenger> passengers = new TreeSet<Passenger>();
    private Itinerary itinerary = new Itinerary();
    boolean running = true;

    public void addItinerary(Airport airport) {
        itinerary.add(airport);
    }
    public void loadPassengers(Airport airport) {
        int n = 1+ ((int) Math.random()*3);
        for (int i=0;i<n; ++i) {
            Passenger passenger = airport.travelers.getRandomPassenger();
            if (passenger != null) {
                passengers.add(passenger);
            }
        }
    }

    public void unloadPassengers(Airport airport) {
        airport.travelers.addAll(passengers);
        passengers.clear();
    }

    @Override public void run() {
        while (running) {
            try {
                Leg leg = itinerary.firstLeg();
                if (leg == null) {
                    Thread.sleep(10);
                } else {
                    loadPassengers(leg.from);
                    System.out.println("Flying " + name + " from " + leg.from + " to " + leg.to + "...");
                    try {
                        Thread.sleep(4000);
                    } finally {
                        unloadPassengers(leg.to);
                    }
                }
            } catch (InterruptedException ex) {
                // ... ignore exception
            }
        }
    }
}
