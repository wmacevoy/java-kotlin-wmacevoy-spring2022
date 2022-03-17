package com.example.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class Airport {
    final String name;
    Airport(String name) { this.name = name; }
    // !!! Shared resource
    final Travelers travelers = new Travelers();
    // shared resource
    final Set<Airplane> airplanes = Collections.synchronizedSet(new TreeSet<Airplane>());

    @Override
    public String toString() {
        return name;
    }
}
