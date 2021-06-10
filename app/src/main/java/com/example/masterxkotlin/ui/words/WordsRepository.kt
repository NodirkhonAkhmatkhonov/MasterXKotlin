package com.example.masterxkotlin.ui.words

import com.example.masterxkotlin.model.WordsDatabase
import com.example.masterxkotlin.model.WordsEntity
import com.example.masterxkotlin.model.WordsItem
import kotlinx.coroutines.flow.Flow

interface WordsRepository {

    suspend fun getAllWords(): ArrayList<WordsItem>
    suspend fun addWord(word: Pair<String, String>)
    suspend fun removeWords(ids: IntArray)
    suspend fun update(id: Int, first: String, second: String)
    suspend fun deleteAllWords()
    suspend fun deleteSomeWords(ids: IntArray)
}