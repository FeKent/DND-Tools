package com.example.dndtools.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Campaign(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val title: String,
    val players: Array<String>,
    val characters: Array<String>,
    val setting: String,
)
