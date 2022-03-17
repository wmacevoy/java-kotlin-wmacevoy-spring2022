package com.example.threading;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Itinerary {
    private ReentrantLock lock = new ReentrantLock();
    private Condition legIsAvailable = lock.newCondition();
    private LinkedList<Airport> destinations = new LinkedList<Airport>();

    public void  add(Airport airport) {
        try (Locker locker = new Locker(lock)) {
            destinations.add(airport);
            if (destinations.size() >= 2) {
                legIsAvailable.signal();
            }
        }
    }

    public int size() {
        try (Locker locker = new Locker(lock)) {
            return destinations.size();
        }
    }

    public Leg nonblockingFirstLeg() {
        try (Locker locker = new Locker(lock)) {
            if (destinations.size() >= 2) {
                Airport from = destinations.getFirst();
                destinations.removeFirst();

                Airport to = destinations.getFirst();
                return new Leg(from,to);
            } else {
                return null;
            }
        }
    }

    public Leg firstLeg() {
        for (;;) {
            try (Locker locker = new Locker(lock)) {
                Leg leg = nonblockingFirstLeg();
                if (leg != null) {
                    return leg;
                }
                try {
                    legIsAvailable.await();
                } catch (InterruptedException e) {
                    // ignored
                }
            }
        }
    }

    public Leg firstLegWithin(long timeout) {
        long t0 = System.currentTimeMillis();
        long t1 = t0;
        for (;;) {
            try (Locker locker = new Locker(lock, timeout-(t1-t0))) {
                if (! locker.locked() ) return null;
                Leg leg = nonblockingFirstLeg();
                if (leg != null) {
                    return leg;
                }
                t1 = System.currentTimeMillis();
                if (timeout <= t1-t0) break;
                try {
                    legIsAvailable.await(timeout-(t1-t0), TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    // ignored
                }
                t1 = System.currentTimeMillis();
            }
        }
        return null;
    }
}
