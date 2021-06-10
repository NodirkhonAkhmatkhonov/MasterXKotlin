package com.example.masterxkotlin.ui.main

interface MainRepository {
    suspend fun getAllWords(): MutableList<WordsPair>
}