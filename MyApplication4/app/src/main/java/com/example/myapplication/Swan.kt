package com.example.myapplication

import com.example.myapplication.Swan
import java.util.*

class Swan(var name: String, var color : String) {
    override fun equals(other: Any?)
      = other is Swan && other.name == name && other.color == color;

    override fun hashCode()
      = Objects.hash(name,color);
}