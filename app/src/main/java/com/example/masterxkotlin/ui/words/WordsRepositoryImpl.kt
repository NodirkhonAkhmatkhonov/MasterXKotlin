package com.example.masterxkotlin.ui.words

import com.example.masterxkotlin.model.WordsDAO
import com.example.masterxkotlin.model.WordsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(private val wordsDAO: WordsDAO): WordsRepository {

    override suspend fun getAllWords(): Flow<List<WordsEntity>> {
        return wordsDAO.getAllWords()
    }

    override fun addWord(word: Pair<String, String>) {
        TODO("Not yet implemented")
    }

    override fun removeWords(ids: IntArray) {
        TODO("Not yet implemented")
    }

    override fun clearAllWords() {
        TODO("Not yet implemented")
    }

}