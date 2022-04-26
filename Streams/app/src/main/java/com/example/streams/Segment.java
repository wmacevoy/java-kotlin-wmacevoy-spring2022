package com.example.streams;

import androidx.annotation.IntRange;

import java.util.Objects;
import java.util.stream.IntStream;

public class Segment {
    public final Dot a;
    public final Dot b;

    static void cross(double [] a, double [] b, double [] axb) {
        axb[0] = a[1] * b[2] - a[2] * b[1];
        axb[1] = a[2] * b[0] - a[0] * b[2];
        axb[2] = a[0] * b[1] - a[1] * b[0];
    }

    static double[] cross(double [] a, double [] b) {
        double [] axb = new double[3];
        cross(a,b,axb);
        return axb;
    }

    static Dot cross(Dot a, Dot b) {
        return new Dot(cross(a.getUVW(),b.getUVW()));
    }

    static double dot(double [] a, double [] b) {
        return a[0]*b[0]+a[1]*b[1]+a[2]*b[2];
    }

    static double length(double [] xyz) {
        return Math.sqrt(Math.pow(xyz[0],2)+Math.pow(xyz[1],2)+Math.pow(xyz[2],2));
    }

    static void scale(double [] xyz,double s) {
        xyz[0] *= s;
        xyz[1] *= s;
        xyz[2] *= s;
    }

    static void rotate(double [] uvw, double [] upole, double radians) {
        double[] fwd = cross(upole, uvw);
        scale(fwd, 1 / length(fwd));

        double up = dot(uvw, upole);
        double c = Math.cos(radians);
        double s = Math.sin(radians);

        for (int i = 0; i < 3; ++i) {
            uvw[i] = (up * upole[i] + c * (uvw[i] - up * upole[i]) + s * fwd[i]);
        }
    }

    Dot forwardKm(double dist) {
        double [] uvw = a.getUVW();
        double [] upole = cross(uvw,b.getUVW());
        scale(upole,1/length(upole));

        double radians = dist/Dot.MEAN_EARTH_RADIUS_KM;
        rotate(uvw,upole,radians);
        return new Dot(uvw);
    }

    Dot nearestTo(Dot c) {
        double s = lengthInKm();
        int n = (int) (s+1);
        return IntStream.rangeClosed(0,n).mapToObj(i -> forwardKm(s*i/n)).min((a,b)->(a.distanceToInKm(c)<b.distanceToInKm(c)));
    }

    Dot nearestToWrong(Dot c) {
        //    https://math.stackexchange.com/questions/2981618/closest-point-on-line-segment-of-a-great-circle

        double [] axb = cross(a.getUVW(),b.getUVW());
        double [] axbxc = cross(axb,c.getUVW());

        Dot q1 = new Dot(cross(axb,axbxc));
        double dq1 = q1.unitdist(c);
        Dot q2 = q1.opposite();
        double dq2 = q2.unitdist(c);

        Dot q;
        double dq;
        if (dq1 <= dq2) {
            q = q1;
            dq = dq1;
        } else {
            q = q2;
            dq = dq2;
        }

        double da = a.unitdist(c);
        double db = b.unitdist(c);

        if (dq < da && dq < db) {
            return q;
        } else if (da <= db) {
            return a;
        } else {
            return b;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Segment)) return false;
        Segment segment = (Segment) o;
        return a.equals(segment.a) && b.equals(segment.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public Segment(Dot a, Dot b) {
        this.a = a;
        this.b = b;
    }

    double lengthInKm() {
        return a.distanceToInKm(b);
    }
}
