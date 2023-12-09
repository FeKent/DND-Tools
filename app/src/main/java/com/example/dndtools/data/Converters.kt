package com.example.dndtools.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String): Array<String> {
        return value.split(",").toTypedArray()
    }

    @TypeConverter
    fun fromArray(value: Array<String>): String {
        return value.joinToString(",")
    }
}