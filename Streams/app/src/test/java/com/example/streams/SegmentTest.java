package com.example.streams;

import junit.framework.TestCase;

import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLContext;

public class SegmentTest extends TestCase {
    public void testNearest() {
        Segment segment = new Segment(GeoMap.GRAND_JUNCTION_CO,GeoMap.PORTLAND_OR);
        Dot near = segment.nearestTo(GeoMap.SALT_LAKE_CITY_UT);
        double dist = near.distanceToInKm(GeoMap.SALT_LAKE_CITY_UT);
        System.out.println(dist);
        assertTrue(near.distanceToInKm(GeoMap.SALT_LAKE_CITY_UT) < 100);
        assertEquals(GeoMap.GRAND_JUNCTION_CO.distanceToInKm(near)+GeoMap.PORTLAND_OR.distanceToInKm(near),
                GeoMap.GRAND_JUNCTION_CO.distanceToInKm(GeoMap.PORTLAND_OR),1e-4);
        assertEquals(segment.nearestTo(GeoMap.MONTROSE_CO),GeoMap.GRAND_JUNCTION_CO);
    }
}