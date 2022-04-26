package com.example.myapplication

import org.junit.Assert.*

import org.junit.Test

class SwanTest {

    @Test
    fun getName() {
        var swan = Swan("alice","blue");
        assertEquals(swan.name,"alice");
    }

    @Test
    fun setName() {
    }

    @Test
    fun getColor() {
    }

    @Test
    fun setColor() {
    }
}