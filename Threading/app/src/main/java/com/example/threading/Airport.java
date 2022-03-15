package com.example.threading;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

public class Airport {
    // !!! Shared resource
    Travelers travelers = new Travelers();
    // shared resource
    ArrayList<Airplane> airplanes = new ArrayList<Airplane>();


}
