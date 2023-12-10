package com.example.dndtools.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OneShot(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val shotTitle: String,
    val players: Int,
    val setting: String,
)