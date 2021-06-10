package com.example.masterxkotlin.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDAO {

    @Query("SELECT * FROM words")
    fun getAllWords(): List<WordsEntity>

    @Query("INSERT INTO words(first, second) VALUES (:first, :second);")
    fun insertWord(first: String, second: String)

    @Query("DELETE FROM words;\n")
    fun deleteAll()

    @Query("DELETE FROM words WHERE id IN (:ids)")
    fun deleteSomeWords(ids: IntArray)

    @Query("UPDATE words SET first = :first, second = :second WHERE id = :id")
    fun update(id: Int, first: String, second: String)
}