package com.example.streams;

import junit.framework.TestCase;
import static com.example.streams.Dot.*;

public class DotTest extends TestCase {

    public void testDistanceToInKm() {
        double expect = 1361;
        double result1 = GRAND_JUNCTION_CO.distanceToInKm(PORTLAND_OR);
        double result2 = PORTLAND_OR.distanceToInKm(GRAND_JUNCTION_CO);
        assertEquals(result1,result2,1e-6);
        assertEquals(result1,expect,1.0);
    }
}