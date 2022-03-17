package com.example.threading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class Airport {
    // !!! Shared resource
    Travelers travelers = new Travelers();
    // shared resource
    Set<Airplane> airplanes = Collections.synchronizedSet(new TreeSet<Airplane>());
}
