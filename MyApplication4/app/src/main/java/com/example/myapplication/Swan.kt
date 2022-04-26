package com.example.myapplication

import com.example.myapplication.Swan
import java.util.*

class Swan(var name: String) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Swan) return false
        return name == o.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name)
    }
}