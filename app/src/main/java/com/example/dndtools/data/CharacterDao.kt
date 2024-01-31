package com.example.dndtools.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterInfoDao {
    @Insert
    suspend fun insertCharacterInfo(characterInfo: CharacterInfo)

    @Delete
    suspend fun delete(characterInfo: CharacterInfo)

    @Update
    suspend fun editInfo(characterInfo: CharacterInfo)

    @Query ("SELECT * FROM CharacterInfo WHERE id = :infoId")
    suspend fun getInfoById(infoId: Int): CharacterInfo?
}


@Dao
interface CharacterProfileDao{
    @Insert
    suspend fun insertCharacterProfile(characterProfile: CharacterProfile)

    @Delete
    suspend fun delete(characterProfile: CharacterProfile)

    @Update
    suspend fun editProfile(characterProfile: CharacterProfile)

    @Query ("SELECT * FROM CharacterProfile WHERE name = :id")
    suspend fun getInfoById(id: Int): CharacterProfile?
}