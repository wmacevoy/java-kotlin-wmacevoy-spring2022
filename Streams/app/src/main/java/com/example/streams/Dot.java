package com.example.streams;

import java.util.Objects;

public class Dot {
    double longitude, latitude;

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

    public Dot(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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
}
