package com.example.dndtools.data

data class Campaign(
    val title: String,
    val players: Array<String>,
    val characters: Array<String>,
    val setting: String,
)
