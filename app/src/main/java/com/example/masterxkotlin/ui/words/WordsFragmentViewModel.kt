package com.example.masterxkotlin.ui.words

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.masterxkotlin.base.BaseViewModel
import com.example.masterxkotlin.model.WordsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WordsFragmentViewModel @Inject constructor(private val wordsRepository: WordsRepository): BaseViewModel<WordsFragmentNavigator>(){

    private val TAG = "WordsFragmentViewModel"

    private var words = ArrayList<WordsItem>()

    val getAllWordsLiveData = MutableLiveData<List<WordsItem>>()
    val deleteFromDBLiveData = MutableLiveData<Boolean>()
    val addNewWordLiveData = MutableLiveData<Boolean>()

    fun addNewWordToDB(word: Pair<String, String>) {
        viewModelScope.launch {
            addNewWordToDBSuspend(word)
            addNewWordLiveData.postValue(true)
        }
    }

    suspend fun addNewWordToDBSuspend(word: Pair<String, String>) = withContext(Dispatchers.IO) {
        wordsRepository.addWord(word)
    }

    fun getAllWords() {
        viewModelScope.launch {
            getAllWordsSuspend()
            getAllWordsLiveData.postValue(words)
        }
    }

    suspend fun getAllWordsSuspend() = withContext(Dispatchers.IO) {
        words = wordsRepository.getAllWords()
    }

    fun deleteAllFromDB() {
        viewModelScope.launch {
            deleteAllFromDBSuspend()
        }
    }

    private suspend fun deleteAllFromDBSuspend() = withContext(Dispatchers.IO) {
        wordsRepository.deleteAllWords()
    }

    fun deleteFromDB(ids: IntArray) {
        viewModelScope.launch {
            deleteFromDBSuspend(ids)
            deleteFromDBLiveData.postValue(true)
        }
    }

    private suspend fun deleteFromDBSuspend(ids: IntArray) = withContext(Dispatchers.IO) {
        wordsRepository.deleteSomeWords(ids)
    }

    fun update(id: Int, first: String, second: String) {
        viewModelScope.launch {
            updateSuspend(id, first, second)
        }
    }

    private suspend fun updateSuspend(id: Int, first: String, second: String) = withContext(Dispatchers.IO) {
        wordsRepository.update(id, first, second)
    }

    fun openAddWordDialog() {
        navigator.openAddWordDialog()
    }

    fun delete() {
        navigator.delete()
    }

    fun deleteAll() {
        navigator.deleteAll()
    }

    fun goBack() {
        navigator.goBack()
    }
}