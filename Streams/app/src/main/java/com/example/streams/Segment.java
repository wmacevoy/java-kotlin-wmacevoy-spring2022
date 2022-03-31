package com.example.streams;

import java.util.Objects;

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

    Dot nearestTo(Dot c) {
        //    https://math.stackexchange.com/questions/2981618/closest-point-on-line-segment-of-a-great-circle

        double [] auvw = a.getUVW();
        double [] buvw = b.getUVW();
        double [] cuvw = c.getUVW();

        double [] axb = cross(auvw,buvw);
        double [] axbxc = cross(axb,cuvw);
        Dot q = new Dot(cross(axb,axbxc));

        double da = a.unitdist(c);
        double db = b.unitdist(c);
        double dq=q.unitdist(c);

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
}
