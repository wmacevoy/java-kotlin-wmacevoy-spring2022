package com.example.streams;

import junit.framework.TestCase;

public class DotTest extends TestCase {
    double decimalDegrees(double degrees, double minutes) {
        return degrees+minutes/60.0;
    }
    double decimalDegrees(double degrees, double minutes, double seconds) {
        return degrees+minutes/60.0+seconds/3600.0;
    }

    Dot portland() {
        return new Dot(-decimalDegrees(122, 40, 55),
                decimalDegrees(45, 31, 12));
    }
    Dot gj() {
        return new Dot(-decimalDegrees(108,34), decimalDegrees(39,4));
    }

    public void testDistanceToInKm() {
        double expect = 1361;
        double result1 = gj().distanceToInKm(portland());
        double result2 = portland().distanceToInKm(gj());
        assertEquals(result1,result2,1e-6);
        assertEquals(result1,expect,1.0);
    }
}