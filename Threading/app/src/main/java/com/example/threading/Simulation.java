package com.example.threading;

import android.service.notification.ZenPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Simulation implements AutoCloseable
{

    List<Airport> airports = new ArrayList<Airport>();
    List<Airplane> airplanes = new ArrayList<Airplane>();
    List<Passenger> passengers = new ArrayList<Passenger>();

    Passenger makeTraveler(Airport airport) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<8; ++i) {
            sb.append('a' + (int) (26*Math.random()));
        }
        String name = sb.toString();
        Passenger passenger = new Passenger(name);
        passengers.add(passenger);
        airport.travelers.add(passenger);
        return passenger;
    }

    int planes = 0;
    Airplane makeAirplane(Airport airport) {
        ++planes;
        String name = "#" + planes;
        Airplane airplane = new Airplane(name, airport);
        airplanes.add(airplane);
        return airplane;
    }

    Airport makeAirport() {
        Airport airport = new Airport();
        for (int i = 0; i < 10; ++i) {
            makeTraveler(airport);
        }
        for (int i=0; i<5; ++i) {
            makeAirplane(airport);
        }
        airports.add(airport);
        return airport;
    }

    void addItineraries() {
        for (Airplane airplane : airplanes) {
            int dest = (int) (airports.size() * Math.random());
            airplane.addItinerary(airports.get(dest));
        }
    }

    void setup() {
        for (int i =0 ; i< 10; ++i) {
            makeAirport();
        }
        for (int i=0; i<100; ++i) {
            addItineraries();
        }
    }

    @Override
    public void close() {
        for (Airplane airplane : airplanes) {
            airplane.close();
        }
    }

    public void join() {
        close();
        for (Airplane airplane : airplanes) {
            airplane.join();
        }
    }
}
