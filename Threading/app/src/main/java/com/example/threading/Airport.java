package com.example.threading;

import java.util.ArrayList;
import java.util.Set;

public class Airport {
    ArrayList<Passenger> travelers = new ArrayList<Passenger>();
    ArrayList<Airplane> airplanes = new ArrayList<Airplane>();

    public void loadPassengers(Airplane airplane) {
        int n = 1+ ((int) Math.random()*3);
        for (int i=0;i<n; ++i) {
            if (!travelers.isEmpty()) {
                int j = (int) Math.random() * travelers.size();
                Passenger p = travelers.get(j);
                airplane.passengers.add(p);
                travelers.remove(j);
            }
        }
    }

    public void unloadPassengers(Airplane airplane) {
        travelers.addAll(airplane.passengers);
        airplane.passengers.clear();
    }
}
