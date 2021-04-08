package com.example.masterxkotlin.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseApplication
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentMainBinding
import com.example.masterxkotlin.di.ViewModelFactory
import com.example.masterxkotlin.ui.words.WordsFragmentViewModel
import timber.log.Timber
import javax.inject.Inject

class MainFragment: BaseFragment<FragmentMainBinding>(), MainFragmentNavigator {

    @Inject
    lateinit var factory: ViewModelFactory

    lateinit var mViewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.getMyComponent().inject(this)

        mViewModel = ViewModelProvider(this, factory).get(MainFragmentViewModel::class.java)
    }

    override fun init(view: View) {
        BaseApplication.getMyComponent().inject(this)

//        mViewModel = ViewModelProviders.of(this, factory).get(MainFragmentViewModel::class.java)

        mViewDataBinding.mainFragmentModel = mViewModel
        mViewDataBinding.lifecycleOwner = this
        mViewModel.navigator = this
    }

    override fun onCardPressed() {
        mViewDataBinding.card
    }

    override fun goBack() {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

    override fun getLayoutId() = R.layout.fragment_main
}
