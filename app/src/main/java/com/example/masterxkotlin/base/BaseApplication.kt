package com.example.masterxkotlin.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.masterxkotlin.BuildConfig
import com.example.masterxkotlin.di.DaggerMyComponent
import com.example.masterxkotlin.di.MyComponent
import com.example.masterxkotlin.utils.Constant
import timber.log.Timber

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        myComponent = DaggerMyComponent
                    .builder()
                    .addContext(this)
                    .build()

        preferences = getSharedPreferences(Constant.CUSTOM_PREFERENCE, Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var baseApplication: Context
        private lateinit var preferences: SharedPreferences
        val getCustomPreference: SharedPreferences get() = preferences

        private lateinit var myComponent: MyComponent

        fun getMyComponent(): MyComponent {
            return myComponent
        }
    }
}