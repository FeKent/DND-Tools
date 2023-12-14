package com.example.dndtools.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface OneShotDao {

    @Insert
    suspend fun insertOneShot(oneShot: OneShot)

    @Delete
    suspend fun delete(oneShot: OneShot)

    @Update
    suspend fun editOneShot(oneShot: OneShot)

    @Query ("SELECT * FROM oneshot")
    fun allOneShots(): Flow<List<OneShot>>

    @Query("SELECT * FROM OneShot WHERE id = :oneShotId")
    suspend fun getOneShotById(oneShotId: Int): OneShot?
}