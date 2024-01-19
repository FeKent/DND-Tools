package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Adventure(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val adventureType: AdventureType,
    val title: String,
    val players: Int,
    val setting: String
) : Parcelable