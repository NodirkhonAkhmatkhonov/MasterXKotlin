package com.example.masterxkotlin.ui.main

import com.example.masterxkotlin.model.WordsDAO
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val wordsDAO: WordsDAO): MainRepository {

    override suspend fun getAllWords(): MutableList<WordsPair> {
        val words = wordsDAO.getAllWords()
        val wordsList = mutableListOf<WordsPair>()

        for (i in words.indices) {
            wordsList.add(WordsPair(words[i].first, words[i].second))
        }
        return wordsList
    }
}