package com.example.threading;

import java.util.LinkedList;

public class Itinerary {
    private LinkedList<Airport> destinations = new LinkedList<Airport>();

    private Object baton = new Object();

    public synchronized void  add(Airport airport) {
        synchronized (baton) {
            destinations.add(airport);
        }
    }

    public int size() {
        synchronized (baton) {
            return destinations.size();
        }
    }

    public Leg firstLeg() {
        synchronized (baton) {
            if (destinations.size() >= 2) {
                Airport from = destinations.getFirst();
                destinations.removeFirst();

                Airport to = destinations.getFirst();
                destinations.removeFirst();

                return new Leg(from,to);
            }
            return null;
        }

    }





}
