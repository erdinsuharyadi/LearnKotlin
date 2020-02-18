package com.erdin.myroom.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: WordRoomEntity)

    @Query("SELECT * FROM word_table ORDER BY id ASC")
    fun getAllWord() : List<WordRoomEntity>

    @Query("SELECT * FROM word_table WHERE id = :wordId")
    fun getWord(wordId: Int): WordRoomEntity

}