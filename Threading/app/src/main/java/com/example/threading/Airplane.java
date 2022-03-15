package com.example.threading;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Airplane implements Runnable {
    String name;
    Set<Passenger> passengers = new TreeSet<Passenger>();
    LinkedList<Airport> itinerary = new LinkedList<Airport>();
    boolean running = true;

    @Override public void run() {
        while (running) {
            try {
                if (itinerary.size() < 2) {
                    Thread.sleep(10);
                } else {
                    Airport at = itinerary.getFirst();
                    at.loadPassengers(this);
                    itinerary.removeFirst();

                    Airport dest = itinerary.getFirst();
                    System.out.println("Flying " + name + " from " + at + " to " + dest + "...");
                    try {
                        Thread.sleep(4000);
                    } finally {
                        dest.unloadPassengers(this);
                    }
                }
            } catch (InterruptedException ex) {
                // ... ignore exception
            }
        }
    }
}
