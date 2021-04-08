package com.example.masterxkotlin.di.module

import com.example.masterxkotlin.ui.words.WordsRepository
import com.example.masterxkotlin.ui.words.WordsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindWordsRepository(wordsRepositoryImpl: WordsRepositoryImpl): WordsRepository
}