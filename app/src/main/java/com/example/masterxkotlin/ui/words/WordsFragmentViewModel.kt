package com.example.masterxkotlin.ui.words

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import com.example.masterxkotlin.base.BaseViewModel
import javax.inject.Inject

class WordsFragmentViewModel @Inject constructor(wordsRepository: WordsRepository): BaseViewModel<WordsFragmentNavigator>(){


    fun goBack() {
        navigator.goBack()
    }
}