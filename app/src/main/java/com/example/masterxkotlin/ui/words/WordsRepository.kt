package com.example.masterxkotlin.ui.words

import com.example.masterxkotlin.model.WordsEntity
import kotlinx.coroutines.flow.Flow

interface WordsRepository {

    suspend fun getAllWords(): Flow<List<WordsEntity>>
    fun addWord(word: Pair<String, String>)
    fun removeWords(ids: IntArray)
    fun clearAllWords()
}