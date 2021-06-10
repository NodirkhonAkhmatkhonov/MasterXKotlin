package com.example.masterxkotlin.ui.words

import android.util.Log
import com.example.masterxkotlin.model.WordsDAO
import com.example.masterxkotlin.model.WordsDatabase
import com.example.masterxkotlin.model.WordsEntity
import com.example.masterxkotlin.model.WordsItem
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.lang.StringBuilder
import javax.inject.Inject

class WordsRepositoryImpl @Inject constructor(private val wordsDAO: WordsDAO): WordsRepository {

    private val TAG = "WordsRepositoryImpl"

    override suspend fun getAllWords(): ArrayList<WordsItem> {
        val words = wordsDAO.getAllWords()
        val wordsArrayList = ArrayList<WordsItem>()

        for (i in words.indices) {
            wordsArrayList.add(WordsItem(i + 1, words[i].id, words[i].first, words[i].second))
        }
        return wordsArrayList
    }

    override suspend fun addWord(word: Pair<String, String>){
        Log.d(TAG, "addWord: ${word.first} -> ${word.second}")
        wordsDAO.insertWord(word.first, word.second)
    }

    override suspend fun removeWords(ids: IntArray) {

    }

    override suspend fun update(id: Int, first: String, second: String) {
        wordsDAO.update(id, first, second)
    }

    override suspend fun deleteAllWords() {
        wordsDAO.deleteAll()
    }

    override suspend fun deleteSomeWords(ids: IntArray) {
        Log.d(TAG, "deleteSomeWords: ${ids.size}")
        wordsDAO.deleteSomeWords(ids)
    }

}