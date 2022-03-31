package com.example.streams;

import android.accessibilityservice.AccessibilityService;

import junit.framework.TestCase;

public class DotTest extends TestCase {

    public void testDistanceToInKm() {
        double expect = 1361;
        double result1 = GeoMap.GRAND_JUNCTION_CO.distanceToInKm(GeoMap.PORTLAND_OR);
        double result2 = GeoMap.PORTLAND_OR.distanceToInKm(GeoMap.GRAND_JUNCTION_CO);
        assertEquals(result1,result2,1e-6);
        assertEquals(result1,expect,1.0);
    }
}