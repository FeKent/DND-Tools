package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@TypeConverters(Converters::class)
data class CharacterInfo(
    @PrimaryKey val id: Int,
    val characterNames: List<String>,
) : Parcelable

@Entity
@Parcelize
data class CharacterProfile(
    @PrimaryKey val name: String,
    val race: String,
    val level: Int,
    val armour: Int,
    val hitPoints: Int
) : Parcelable