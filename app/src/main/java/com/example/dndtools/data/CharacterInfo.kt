package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class CharacterInfo(
    @PrimaryKey val id: Int,
    val characterNames: List<String>,
) : Parcelable

data class CharacterProfile(
    val name: String,
    val race: String,
    val level: Int,
    val armour: Int,
    val hitPoints: Int
)