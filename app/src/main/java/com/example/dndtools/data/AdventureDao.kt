package com.example.dndtools.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AdventureDao {
    @Insert
    suspend fun insertAdventure(adventure: Adventure)

    @Delete
    suspend fun delete(adventure: Adventure)

    @Update
    suspend fun editAdventure(adventure: Adventure)

    @Query ("SELECT * FROM adventure")
    fun allAdventures(): Flow<List<Adventure>>

    @Query("SELECT * FROM Adventure WHERE id = :adventureId")
    suspend fun getAdventureById(adventureId: Int): Adventure?
}