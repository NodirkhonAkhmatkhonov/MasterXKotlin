package com.example.masterxkotlin.ui.words

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseApplication
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentWordsBinding
import com.example.masterxkotlin.di.ViewModelFactory
import com.example.masterxkotlin.ui.main.MainFragmentViewModel
import javax.inject.Inject

class WordsFragment : BaseFragment<FragmentWordsBinding>(), WordsFragmentNavigator {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mViewModel: WordsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.getMyComponent().inject(this)

        mViewModel = ViewModelProvider(this, factory).get(WordsFragmentViewModel::class.java)
    }

    override fun init(view: View) {
//        mViewModel = ViewModelProvider(this).get(WordsFragmentViewModel::class.java)
        mViewDataBinding.wordsFragmentModel = mViewModel
        mViewDataBinding.lifecycleOwner = this
        mViewModel.navigator = this
    }


    override fun goBack() {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

    override fun getLayoutId() = R.layout.fragment_words
}