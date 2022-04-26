package com.example.streams;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Path extends ArrayList<Segment> {
    Path() { super(); }
    Path(int capacity) { super(capacity); }

    @RequiresApi(api = Build.VERSION_CODES.N)
    double lengthInKm() {
        return parallelStream().mapToDouble(segment -> segment.lengthInKm()).sum();
    }

    double lengthInKmSlow() {
        double ans = 0;
        for (Segment segment : this) {
            ans += segment.lengthInKm();
        }
        return ans;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    double distanceToInKmInParallel(Dot to) {
        return parallelStream()
                .mapToDouble(segment -> segment.nearestTo(to).distanceToInKm(to))
                .min()
                .orElse(Double.MAX_VALUE);
    }
}
