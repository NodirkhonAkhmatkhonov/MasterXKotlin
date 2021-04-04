package com.example.masterxkotlin.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface WordsDAO {

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<WordsEntity>

//    @Query("INSERT INTO words(pairs) VALUES (:item)")
//    fun insertWord(item: WordsItem)
}