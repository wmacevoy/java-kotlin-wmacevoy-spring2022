package com.example.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Simulation
{
    Passenger makePassenger() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<8; ++i) {
            sb.append('a' + (int) (26*Math.random()));
        }
        return new Passenger(sb.toString());
    }
    int planes = 0;
    Airplane makeAirplane() {
        Airplane airplane = new Airplane();
        airplane.name = "airplane # " + planes;
        ++planes;
        Thread pilot = new Thread(airplane);
        pilot.start();

        return airplane;
    }

    Airport makeAirport() {
        Airport airport = new Airport();
        for (int i=0; i<10; ++i) {
            airport.travelers.add(makePassenger());
        }
        for (int i=0; i<5; ++i) {
            Airplane airplane = makeAirplane();
            airport.airplanes.add(airplane);
            airplane.itinerary.add(airport);
        }
        return airport;
    }

    void addItineraries(List<Airplane> airplanes,
                        List<Airport> destinations) {
        for (Airplane airplane : airplanes) {
            int dest = (int) (destinations.size() * Math.random());
            airplane.itinerary.add(destinations.get(dest));
        }
    }

    List<Airport> airports = new ArrayList<Airport>();
    List<Airplane> airplanes = new ArrayList<Airplane>();
    void setup() {
        for (int i =0 ; i< 10; ++i) {
            Airport airport = makeAirport();
            airports.add(airport);
            airplanes.addAll(airport.airplanes);
        }
        for (int i=0; i<100; ++i) {
            addItineraries(airplanes,airports);
        }
    }
}
