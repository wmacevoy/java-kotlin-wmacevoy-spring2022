package com.example.threading;

import android.service.notification.ZenPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Simulation implements AutoCloseable
{
    static int rand(int a, int b) {
        return (int)(a + (b-a+1)*Math.random());
    }

    static String rand(String [] options) {
        return options[rand(0,options.length-1)];
    }

    List<Airport> airports = new ArrayList<Airport>();
    List<Airplane> airplanes = new ArrayList<Airplane>();
    List<Passenger> passengers = new ArrayList<Passenger>();

    String [] firstNames = new String[] { "Alice", "Bob", "Cindy", "Doug", "Ella", "Francis"};
    String [] lastNames = new String[] { "O'Roark", "Smith", "Fernandez", "Lopez"};

    String randPattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<pattern.length(); i += Character.charCount(Character.codePointAt(pattern,i))) {
            int codePoint = Character.codePointAt(pattern,i);
            switch(codePoint) {
                case 'a': sb.append((char) rand('a','z')); break;
                case 'A': sb.append((char) rand('A','Z')); break;
                case '#': sb.append((char) rand('0','9')); break;
                default: sb.appendCodePoint(codePoint); break;
            }
        }
        return sb.toString();
    }

    Passenger makeTraveler(Airport airport) {
        String name = rand(firstNames)
                + " " + randPattern("Aaaaaaaa")
                + " " + rand(lastNames);

        Passenger passenger = new Passenger(name);
        passengers.add(passenger);
        airport.travelers.add(passenger);
        return passenger;
    }

    String [] airlineNames = new String[] { "american", "quantas", "delta", "turkish"};

    Airplane makeAirplane(Airport airport) {
        String name = rand(airlineNames) + " #" + randPattern("A#A#");
        Airplane airplane = new Airplane(name, airport);
        airplanes.add(airplane);
        return airplane;
    }

    Airport makeAirport() {

        String name = randPattern("AAAA");
        Airport airport = new Airport(name);
        for (int i = 0; i < 100; ++i) {
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
