package com.example.dndtools.data

import androidx.room.PrimaryKey

data class PlayerInfo(
    @PrimaryKey val id: Int,
    val characterNames: List<String>,
)

data class CharacterProfile(
    val name: String,
    val race: String,
    val level: Int,
    val armour: Int,
    val hitPoints: Int
)