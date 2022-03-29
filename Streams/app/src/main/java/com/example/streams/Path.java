package com.example.streams;

import java.util.ArrayList;

public class Path extends ArrayList<Dot> {
    double lengthInKm() {
        double ans = 0;
        for (int i = 0; i < size() - 1; ++i) {
            ans += get(i).distanceToInKm(get(i + 1));
        }
        return ans;
    }
}
