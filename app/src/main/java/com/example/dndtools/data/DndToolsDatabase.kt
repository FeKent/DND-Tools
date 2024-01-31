package com.example.dndtools.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Adventure::class, CharacterInfo::class, CharacterProfile::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DndToolsDatabase : RoomDatabase() {
    abstract fun adventureDao() : AdventureDao

    abstract fun characterInfoDao() : CharacterInfoDao

    abstract fun characterProfileDao() : CharacterProfileDao

}