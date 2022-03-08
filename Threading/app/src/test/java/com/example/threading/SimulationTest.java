package com.example.threading;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimulationTest {
    @Test
    public void sim() throws Exception {
        Simulation sim = new Simulation();
        System.out.println("start.");
        sim.setup();
        Thread.sleep(30_000);
        System.out.println("stop.");
    }

}