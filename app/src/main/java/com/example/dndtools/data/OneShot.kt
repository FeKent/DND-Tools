package com.example.dndtools.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class OneShot(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val shotTitle: String,
    val shotPlayers: Int,
    val shotSetting: String,
) : Parcelable, Adventure {
    override val title: String
        get() = shotTitle

    override val numPlayers: Int
        get() = shotPlayers
}