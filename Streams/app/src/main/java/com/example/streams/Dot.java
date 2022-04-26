package com.example.streams;

import java.util.Objects;

public class Dot {
    final double longitude, latitude;

    Dot opposite() {
        return new Dot(longitude,latitude+180.0);
    }

    void toUVW(double [] uvw) {
        double phi = Math.toRadians(latitude);
        double lambda = Math.toRadians(longitude);
        uvw[0] = Math.cos(lambda)*Math.cos(phi);
        uvw[1] = Math.sin(lambda)*Math.cos(phi);
        uvw[2] = Math.sin(phi);
    }

    double[] getUVW() {
        double [] uvw = new double[3];
        toUVW(uvw);
        return uvw;
    }

    void toXYZ(double [] xyz) {
        toUVW(xyz);
        xyz[0] *= MEAN_EARTH_RADIUS_KM;
        xyz[1] *= MEAN_EARTH_RADIUS_KM;
        xyz[2] *= MEAN_EARTH_RADIUS_KM;
    }

    // https://en.wikipedia.org/wiki/Great-circle_distance
    double unitdist(Dot to) {
        double phi1 = Math.toRadians(latitude);
        double phi2 = Math.toRadians(to.latitude);
        double lambda1 = Math.toRadians(longitude);
        double lambda2 = Math.toRadians(to.longitude);

        double dx = Math.cos(phi2) * Math.cos(lambda2) -
                Math.cos(phi1) * Math.cos(lambda1);
        double dy = Math.cos(phi2) * Math.sin(lambda2) -
                Math.cos(phi1) * Math.sin(lambda1);
        double dz = Math.sin(phi2) - Math.sin(phi1);

        double c = Math.sqrt(dx * dx + dy * dy + dz * dz);

        double dsigma = Math.asin(c / 2);

        return 2*dsigma;
    }

    public static final double MEAN_EARTH_RADIUS_KM = 6371.0088;

    double distanceToInKm(Dot to) {
        return unitdist(to) * MEAN_EARTH_RADIUS_KM;
    }

    public static double fmod(double x, double y) {
        if (y < 0) {
            x = -x;
            y = -y;
        }
        double u = x / y;
        u = u - Math.floor(u);
        return y * u;
    }

    public Dot(double longitude, double latitude) {
        latitude = fmod(latitude+180.0,360.0)-180.0;
        if (latitude > 90.0) {
            latitude = 180.0 - latitude;
            longitude += 180;
        } else if (latitude < -90.0) {
            latitude = -180 + latitude;
            longitude += 180;
        }
        longitude = fmod(longitude+180.0,360.0)-180.0;

        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Dot(double [] xyz) {
        double r = Math.sqrt(Math.pow(xyz[0],2)+Math.pow(xyz[1],2)+Math.pow(xyz[2],2));
        double phi = Math.asin(xyz[2]/r);
        double lambda = (xyz[0] != 0 || xyz[1] != 0) ? Math.atan2(xyz[1],xyz[0]) : 0.0;
        this.longitude = Math.toDegrees(lambda);
        this.latitude = Math.toDegrees(phi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dot)) return false;
        Dot dot = (Dot) o;
        return Double.compare(dot.longitude, longitude) == 0 && Double.compare(dot.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    public static final Dot ORIGIN = new Dot(0,0);
    public static final Dot NORTH_POLE = new Dot(0,90);
    public static final Dot SOUTH_POLE = new Dot(0,-90);
    public static final Dot EAST_POLE = new Dot(90, 0);
    public static final Dot WEST_POLE = new Dot(-90,0);
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
