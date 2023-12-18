package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Campaign(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    override val title: String,
    val players: Array<String>,
    val characters: Array<String>,
    val setting: String,
) : Parcelable, Adventure
