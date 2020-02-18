package com.erdin.myroom.marvel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(char: CharacterEntity)

    @Query("SELECT * FROM character_table")
    fun getAllChar() : List<CharacterEntity>

    @Query("SELECT * FROM character_table ORDER BY id LIMIT 1")
    fun getLastChar(): List<CharacterEntity>
}