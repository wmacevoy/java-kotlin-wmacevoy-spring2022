package com.example.streams;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PathTest extends TestCase {
    Dot randDot() {
        return new Dot(Math.random()*360.0-180.0, Math.random()*180.0-90.0);
    }

    Path randPath(int n) {
        Path ans = new Path(n);
        Dot dot = randDot();
        for (int i=0; i<n; ++i) {
            Dot nextDot = randDot();
            ans.add(new Segment(dot,nextDot));
            dot=nextDot;
        }
        return ans;
    }

    Path randPathInParallel(int n) {
        List<Dot> dots = Stream
                .generate(()->randDot())
                .limit(n+1)
                .collect(Collectors.toList());

        Path ans = new Path(n);

        IntStream.range(0, n)
                .mapToObj((i)->new Segment(dots.get(i),dots.get(i+1)))
                .forEachOrdered(segment -> ans.add(segment));

        return ans;
    }

    int n = 100_000;
    Path path = randPath(n);

    public void testLengthInKm() {
        path.lengthInKm();
    }

    public void testLengthInKmSlow() {
        path.lengthInKmSlow();
    }
}