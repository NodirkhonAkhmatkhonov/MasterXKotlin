package com.example.masterxkotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.masterxkotlin.base.BaseViewModel
import com.example.masterxkotlin.model.WordsItem
import com.example.masterxkotlin.ui.words.WordsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val wordsRepository: MainRepository ): BaseViewModel<MainFragmentNavigator>() {

    val getAllWordsLiveData = MutableLiveData<MutableList<WordsPair>>()
    var allWords = mutableListOf<WordsPair>()

    fun getAllWords() {
        viewModelScope.launch {
            getAllWordsSuspend()
            getAllWordsLiveData.postValue(allWords)
        }
    }

    private suspend fun getAllWordsSuspend() = withContext(Dispatchers.IO) {
        allWords = wordsRepository.getAllWords().toMutableList()
    }

    fun onReversePressed() {
        navigator.onReversePressed()
    }

    fun goBack() {
        navigator.goBack()
    }

    fun onCardPressed() {
        navigator.onCardPressed()
    }
}