package com.example.dndtools.data

import androidx.room.Entity

@Entity
data class Campaign(
    val title: String,
    val players: Array<String>,
    val characters: Array<String>,
    val setting: String,
)
