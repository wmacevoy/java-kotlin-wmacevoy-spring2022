package com.example.streams;

import junit.framework.TestCase;
import static com.example.streams.Dot.*;

public class SegmentTest extends TestCase {
    public void testCross() {
        // North is on the left
        assertEquals(Segment.cross(ORIGIN, new Dot(1, 0)).distanceToInKm(NORTH_POLE),0, 1e-6);
        assertEquals(Segment.cross(ORIGIN, new Dot(-1, 0)).distanceToInKm(SOUTH_POLE),0,1e-6);
        assertEquals(Segment.cross(ORIGIN, new Dot(0, 1)).distanceToInKm(WEST_POLE),0,1e-6);
        assertEquals(Segment.cross(ORIGIN, new Dot(0, -1)).distanceToInKm(EAST_POLE),0,1e-6);
    }

    public void testForward() {
        Segment segment = new Segment(GRAND_JUNCTION_CO,PORTLAND_OR);
        assertEquals(0, segment.forwardKm(0).distanceToInKm(segment.a),1e-6);
        assertEquals(0,segment.forwardKm(segment.lengthInKm()).distanceToInKm(segment.b),1e-6);
        Dot midpoint = segment.forwardKm(segment.lengthInKm()/2.0);
        assertEquals(segment.lengthInKm()/2.0,midpoint.distanceToInKm(segment.a),1e-4);
        assertEquals(segment.lengthInKm()/2.0,midpoint.distanceToInKm(segment.b),1e-4);
    }
    public void testNearest() {
        Segment segment = new Segment(GRAND_JUNCTION_CO,PORTLAND_OR);
        Dot near = segment.nearestTo(SALT_LAKE_CITY_UT);
        double dist = near.distanceToInKm(SALT_LAKE_CITY_UT);
        System.out.println(dist);
        assertTrue(near.distanceToInKm(SALT_LAKE_CITY_UT) < 100);
        assertEquals(GRAND_JUNCTION_CO.distanceToInKm(near)+PORTLAND_OR.distanceToInKm(near),
                GRAND_JUNCTION_CO.distanceToInKm(PORTLAND_OR),1e-4);
        assertEquals(segment.nearestTo(MONTROSE_CO),GRAND_JUNCTION_CO);
    }
}