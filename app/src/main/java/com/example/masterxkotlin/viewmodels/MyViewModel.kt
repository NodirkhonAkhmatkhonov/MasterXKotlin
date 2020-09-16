package com.example.masterxkotlin.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.masterxkotlin.repositories.MyRepository

class MyViewModel: ViewModel() {

    private var text = MutableLiveData<String>()
    private var repository: MyRepository? = null

    fun init() {
        repository = MyRepository.instance
        text = repository!!.getData()
    }

    fun getText(): MutableLiveData<String> {
        if (repository == null) {
            init()
        }

        return text
    }

    fun getPlainText(): String {
        return "sadfas"
    }
}