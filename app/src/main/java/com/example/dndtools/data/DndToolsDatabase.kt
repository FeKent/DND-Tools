package com.example.dndtools.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Adventure::class], version = 1, exportSchema = false)
abstract class DndToolsDatabase : RoomDatabase() {
    abstract fun adventureDao() : AdventureDao

}

@Database(entities = [CharacterInfo::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterInfoDao() : CharacterInfoDao
}

@Database(entities = [CharacterProfile::class], version = 1, exportSchema = false)
abstract class CharacterProfileDatabase : RoomDatabase() {
    abstract fun characterProfileDao() : CharacterProfileDao
}