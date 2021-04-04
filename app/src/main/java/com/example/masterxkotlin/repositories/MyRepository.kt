package com.example.masterxkotlin.repositories

import androidx.lifecycle.LiveData
import com.example.masterxkotlin.model.WordsDatabase
import com.example.masterxkotlin.model.WordsEntity
import com.example.masterxkotlin.model.WordsItem

class MyRepository {

    suspend fun getAllWords(db: WordsDatabase) : List<WordsEntity>{

        return db.wordsDao().getAllWords()
    }

//    fun insertWord(db: WordsDatabase, item: WordsItem) {
//        db.wordsDao().insertWord(item)
//    }

    private object HOLDER {
        val INSTANCE = MyRepository()
    }

    companion object {
        val instance: MyRepository by lazy { HOLDER.INSTANCE }
    }
}