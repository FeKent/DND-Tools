package com.example.dndtools.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> = value.split(", ")

    @TypeConverter
    fun fromList(value: List<String>): String = value.joinToString(", ")

}