package com.example.masterxkotlin.di

import android.content.Context
import com.example.masterxkotlin.di.module.RepoModule
import com.example.masterxkotlin.di.module.ViewModelModule
import com.example.masterxkotlin.di.module.WordsModule
import com.example.masterxkotlin.ui.activity.MainActivity
import com.example.masterxkotlin.ui.main.MainFragment
import com.example.masterxkotlin.ui.words.WordsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [WordsModule::class, ViewModelModule::class, RepoModule::class])
interface MyComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun addContext(context: Context): Builder

        fun build(): MyComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(wordsFragment: WordsFragment)
}