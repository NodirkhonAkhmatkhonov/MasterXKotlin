package com.example.masterxkotlin.ui.main

import android.icu.number.Scale
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionSet
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseApplication
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentMainBinding
import com.example.masterxkotlin.di.ViewModelFactory
import com.example.masterxkotlin.model.WordsItem
import com.example.masterxkotlin.ui.words.WordsFragmentViewModel
import com.google.android.material.transition.ScaleProvider
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class MainFragment: BaseFragment<FragmentMainBinding>(), MainFragmentNavigator {

    @Inject
    lateinit var factory: ViewModelFactory

    private val TAG = "MainFragment"

    lateinit var mViewModel: MainFragmentViewModel
    private var words = mutableListOf<WordsPair>()
    private var curIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BaseApplication.getMyComponent().inject(this)

        mViewModel = factory.create(MainFragmentViewModel::class.java)

        mViewModel.getAllWords()

        mViewModel.getAllWordsLiveData.observe(this, Observer {
            words = it
            Log.d(TAG, "onCreate: ${words.size}")
            setWords()
        })
    }

    override fun init(view: View) {
        BaseApplication.getMyComponent().inject(this)

        mViewDataBinding.mainFragmentModel = mViewModel
        mViewDataBinding.lifecycleOwner = this
        mViewModel.navigator = this
    }

    override fun onCardPressed() {
        if (mViewDataBinding.cardSecond.visibility == View.VISIBLE) {
            mViewDataBinding.cardSecond.visibility = View.GONE
            mViewDataBinding.btnShow.text = "SHOW"
            setWords()
        } else {
            mViewDataBinding.cardSecond.visibility = View.VISIBLE
            mViewDataBinding.btnShow.text = "NEXT"
        }
    }

    override fun onReversePressed() {
        words.forEach {
            val secondPart = it.secondPart
            it.secondPart = it.firstPart
            it.firstPart = secondPart
        }

        mViewDataBinding.tvFirst.text = words[curIndex].firstPart
        mViewDataBinding.tvSecond.text = words[curIndex].secondPart
    }

    private fun setWords() {
        if (words.size < 1) {
            mViewDataBinding.ivEmptyBox.visibility = View.VISIBLE
            return
        }

        mViewDataBinding.ivEmptyBox.visibility = View.GONE
        mViewDataBinding.linearLayout.visibility = View.VISIBLE
        mViewDataBinding.btnShow.isClickable = true

        mViewDataBinding.cardSecond.visibility = View.GONE

        val random = (0 until words.size).random()

        curIndex = random

        mViewDataBinding.tvFirst.text = words[random].firstPart
        mViewDataBinding.tvSecond.text = words[random].secondPart
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun getLayoutId() = R.layout.fragment_main
}
