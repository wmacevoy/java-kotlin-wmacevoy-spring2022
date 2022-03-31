package com.example.streams;


import java.util.ArrayList;
import java.util.Objects;


public class GeoMap {
    public static final Dot PORTLAND_OR = new Dot(toDecimalDegrees(122, 40, 55),
                toDecimalDegrees(45, 31, 12));
    public static final Dot GRAND_JUNCTION_CO = new Dot(toDecimalDegrees(108,34), toDecimalDegrees(39,4));

    public static final Dot SALT_LAKE_CITY_UT = new Dot(-toDecimalDegrees(111,53,28),toDecimalDegrees(40,45,39));

    public static final Dot MONTROSE_CO = new Dot(
            -toDecimalDegrees(107,51,56),
            toDecimalDegrees(38,28,37));

    public static double toDecimalDegrees(double degrees, double minutes) {
        return degrees+minutes/60.0;
    }

    public static double toDecimalDegrees(double degrees, double minutes, double seconds) {
        return degrees + minutes / 60.0 + seconds / 3600.0;
    }
}
