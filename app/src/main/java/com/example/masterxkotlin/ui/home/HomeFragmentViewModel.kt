package com.example.masterxkotlin.ui.home

import com.example.masterxkotlin.base.BaseViewModel
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(): BaseViewModel<HomeFragmentNavigator>() {

    fun onPlaygroundPressed() {
        navigator.onPlaygroundPressed()
    }

    fun onWordsPressed() {
        navigator.onWordsPressed()
    }

    fun onExitPressed() {
        navigator.onExitPressed()
    }
}