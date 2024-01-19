package com.example.dndtools.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Adventure::class], version = 1, exportSchema = false)
abstract class DndToolsDatabase : RoomDatabase() {
    abstract fun adventureDao() : AdventureDao

}