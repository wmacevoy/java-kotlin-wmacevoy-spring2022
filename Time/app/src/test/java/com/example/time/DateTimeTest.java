package com.example.time;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTimeTest {

    @Test
    public void milliseconds() {
        System.out.println(new DateTime().milliseconds());
    }

    @Test
    public void iso8601() {
        System.out.println(new DateTime().iso8601());
    }
}