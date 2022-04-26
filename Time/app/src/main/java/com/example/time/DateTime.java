package com.example.time;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.LocalDateTime;

public class DateTime {
    long milliseconds() {
        return System.currentTimeMillis();
    }

    long milliseconds(LocalDateTime localDateTime) {
        localDateTime.
    }

    LocalDateTime localDateTime() {
        return LocalDateTime.of(instant)
    }

    String iso8601(long milliseconds) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String iso8601(){
        return Instant.now().toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String iso8601(){
        return Instant.now().toString();
    }




}
