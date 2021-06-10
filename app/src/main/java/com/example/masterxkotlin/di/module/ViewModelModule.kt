package com.example.masterxkotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.masterxkotlin.di.ViewModelFactory
import com.example.masterxkotlin.di.ViewModelKey
import com.example.masterxkotlin.ui.home.HomeFragmentViewModel
import com.example.masterxkotlin.ui.main.MainFragmentViewModel
import com.example.masterxkotlin.ui.words.WordsFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey( HomeFragmentViewModel::class )
    abstract fun bindHomeFragmentViewModel(homeFragmentViewModel: HomeFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey( MainFragmentViewModel::class )
    abstract fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey( WordsFragmentViewModel::class )
    abstract fun bindWordsFragmentViewModel(wordsFragmentViewModel: WordsFragmentViewModel): ViewModel
}