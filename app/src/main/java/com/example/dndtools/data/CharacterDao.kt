package com.example.dndtools.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface CharacterInfoDao {
    @Insert
    suspend fun insertCharacterInfo(characterInfo: CharacterInfo)

    @Delete
    suspend fun delete(characterInfo: CharacterInfo)

    @Update
    suspend fun editInfo(characterInfo: CharacterInfo)

    @Query ("SELECT * FROM CharacterInfo WHERE id = id")
    suspend fun getInfoById(id: Int): CharacterInfo?
}

interface CharacterProfileDao{
    @Insert
    suspend fun insertCharacterProfile(characterProfile: CharacterProfile)

    @Delete
    suspend fun delete(characterProfile: CharacterProfile)

    @Update
    suspend fun editProfile(characterProfile: CharacterProfile)

    @Query ("SELECT * FROM CharacterInfo WHERE id = id")
    suspend fun getInfoById(id: Int): CharacterProfile?
}