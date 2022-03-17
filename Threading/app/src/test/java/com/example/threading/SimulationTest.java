package com.example.threading;

import static org.junit.Assert.*;

import org.junit.Test;


// ./gradlew :app:cleanTestDebugUnitTest :app:testDebugUnitTest --tests "com.example.threading.SimulationTest"
public class SimulationTest {
    @Test
    public void sim() throws Exception {

        System.out.println("start.");
        try (Simulation sim = new Simulation()) {
            sim.setup();
            Thread.sleep(60_000);
            sim.join();
        }
        System.out.println("stop.");
    }

}