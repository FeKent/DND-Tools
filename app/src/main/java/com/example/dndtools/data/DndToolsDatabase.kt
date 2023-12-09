package com.example.dndtools.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Campaign::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DndToolsDatabase : RoomDatabase() {
    abstract fun campaignDao() : CampaignDao
}