package com.example.masterxkotlin.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.masterxkotlin.base.BaseViewModel
import com.example.masterxkotlin.model.WordsDatabase
import com.example.masterxkotlin.model.WordsEntity
import com.example.masterxkotlin.repositories.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(application: Application): AndroidViewModel(application) {

    private var repository: MyRepository? = null
    private val db: WordsDatabase
    var words = MutableLiveData<List<WordsEntity>>()

    init {
        repository = MyRepository.instance
        db = Room.databaseBuilder(
            application,
            WordsDatabase::class.java, "words.db"
        ).build()

        GlobalScope.launch {
//            words.postValue(repository!!.getAllWords(db))
        }

    }

    fun onHFPlaygroundPressed() {

    }
}