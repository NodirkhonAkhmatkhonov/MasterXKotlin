package com.example.masterxkotlin.di.module

import com.example.masterxkotlin.ui.main.MainRepository
import com.example.masterxkotlin.ui.main.MainRepositoryImpl
import com.example.masterxkotlin.ui.words.WordsRepository
import com.example.masterxkotlin.ui.words.WordsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {

    @Binds
    fun bindWordsRepository(wordsRepositoryImpl: WordsRepositoryImpl): WordsRepository

    @Binds
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}