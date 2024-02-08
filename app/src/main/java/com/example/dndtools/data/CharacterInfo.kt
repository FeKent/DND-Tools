package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize


@Entity(
    foreignKeys = [ForeignKey(
        entity = Adventure::class,
        parentColumns = ["id"],
        childColumns = ["adventureId"],
        onDelete = ForeignKey.CASCADE
    )], indices = [Index("adventureId")]
)
@Parcelize
@TypeConverters(Converters::class)
data class CharacterInfo(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val characterNames: List<String>,
    val adventureId: Int,
) : Parcelable

@Entity
@Parcelize
data class CharacterProfile(
    @PrimaryKey val name: String,
    val race: String,
    val mainClass: String,
    val level: Int,
    val armour: Int,
    val hitPoints: Int,
    val spellSave: Int
) : Parcelable