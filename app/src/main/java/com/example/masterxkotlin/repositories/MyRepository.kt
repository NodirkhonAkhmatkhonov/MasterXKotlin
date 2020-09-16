package com.example.masterxkotlin.repositories

import androidx.lifecycle.MutableLiveData

class MyRepository {

    private var text: String = "Yeah"

    fun getData(): MutableLiveData<String> {
        val data: MutableLiveData<String> = MutableLiveData()
        data.value = text
        return data
    }

    private object HOLDER {
        val INSTANCE = MyRepository()
    }

    companion object {
        val instance: MyRepository by lazy { HOLDER.INSTANCE }
    }
}