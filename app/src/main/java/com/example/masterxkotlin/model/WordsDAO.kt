package com.example.masterxkotlin.model

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDAO {

    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<WordsEntity>>

//    @Query("INSERT INTO words(pairs) VALUES (:item)")
//    fun insertWord(item: WordsItem)
}