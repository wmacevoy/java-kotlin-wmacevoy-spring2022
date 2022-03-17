package com.example.threading;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Airplane implements Runnable, Comparable<Airplane>, AutoCloseable {
    private final String name;
    private final Thread pilot;
    private Airport airport;

    public Airplane(String name, Airport airport) {
        this.name = name;
        this.airport = airport;
        airport.airplanes.add(this);
        itinerary.add(airport);
        pilot = new Thread(this);
        pilot.start();
    }
    private Passengers passengers = new Passengers();
    private Itinerary itinerary = new Itinerary();
    boolean running = true;

    @Override
    public void close() {
        running = false;
        pilot.interrupt();
    }

    public void join() {
        for (;;) {
            try {
                close();
                pilot.join(1000);
                return;
            } catch (InterruptedException e) {
            }
        }
    }

    public void addItinerary(Airport airport) {
            itinerary.add(airport);
    }

    public void loadPassengers() {
        int n = Simulation.rand(1,3);
        int m = 0;
        for (int i=0;i<n; ++i) {
            Passenger passenger = airport.travelers.getRandomPassenger();
            if (passenger != null) {
                passengers.add(passenger);
                ++m;
            }
        }
        System.out.println(this + " loaded " + m  + " passengers " + passengers);
    }

    public void unloadPassengers() {
        Set<Passenger> unloaded = passengers.unload();
        airport.travelers.addAll(unloaded);
        System.out.println(airport + " unloaaded " + unloaded + " from " + this);
    }

    @Override public void run() {
        while (running) {
            try {
                Leg leg = itinerary.firstLegWithin(1000);
                if (leg == null) continue;
                airport = leg.from;
                loadPassengers();
                airport.airplanes.remove(this);
                airport = null;
                System.out.println("Flying " + this + " from " + leg.from + " to " + leg.to + "...");
                try {
                    Thread.sleep(400);
                } finally {
                    airport = leg.to;
                    airport.airplanes.add(this);
                    unloadPassengers();
                }
            } catch (InterruptedException ex) {
                // ... ignore exception
            }
        }
        System.out.println("" + this + " stopped.");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Airplane airplane) {
        return name.compareTo(airplane.name);
    }

    @Override
    public boolean equals(Object object) {
        return compareTo((Airplane) object) == 0;
    }
}
