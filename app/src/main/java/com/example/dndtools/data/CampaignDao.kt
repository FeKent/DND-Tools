package com.example.dndtools.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CampaignDao {
    @Insert
    suspend fun insertCampaign(campaign: Campaign)

    @Delete
    suspend fun delete(campaign: Campaign)

    @Update
    suspend fun editCampaign(campaign: Campaign)

    @Query ("SELECT * FROM campaign")
    fun allCampaigns(): Flow<List<Campaign>>
}