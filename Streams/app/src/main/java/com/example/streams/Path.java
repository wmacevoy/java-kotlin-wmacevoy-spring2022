package com.example.streams;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Path extends ArrayList<Dot> {
    double lengthInKm() {
        double ans = 0;
        for (int i = 0; i < size() - 1; ++i) {
            ans += get(i).distanceToInKm(get(i + 1));
        }
        return ans;
    }

    double distanceToInKm(Dot to) {
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < size(); ++i) {
            minDist = Math.min(minDist, get(i).distanceToInKm(to));
        }
        return minDist;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    double distanceToInKmInParallel(Dot to) {
        return parallelStream()
                .mapToDouble(dot -> dot.distanceToInKm(to))
                .min()
                .orElse(Double.MAX_VALUE);
    }
}
