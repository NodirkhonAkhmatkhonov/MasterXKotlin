package com.example.masterxkotlin.di.module

import android.content.Context
import com.example.masterxkotlin.model.WordsDAO
import com.example.masterxkotlin.model.WordsDatabase
import dagger.Module
import dagger.Provides

@Module
class WordsModule {

    @Provides
    fun provideWordsDatabase(context: Context): WordsDatabase {
        return WordsDatabase.invoke(context)
    }

    @Provides
    fun provideWordsDao(wordsDatabase: WordsDatabase): WordsDAO {
        return wordsDatabase.wordsDao()
    }
}