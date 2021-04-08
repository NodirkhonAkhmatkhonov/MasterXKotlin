package com.example.masterxkotlin.ui.main

import com.example.masterxkotlin.base.BaseViewModel
import com.example.masterxkotlin.ui.words.WordsRepository
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(): BaseViewModel<MainFragmentNavigator>() {

    fun initWords() {
        //todo
    }

    fun goBack() {
        navigator.goBack()
    }

    fun onCardPressed() {
        navigator.onCardPressed()
    }
}