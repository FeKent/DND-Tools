package com.example.dndtools.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Campaign::class], version = 1)
abstract class DndToolsDatabase : RoomDatabase() {
    abstract fun campaignDao() : CampaignDao
}