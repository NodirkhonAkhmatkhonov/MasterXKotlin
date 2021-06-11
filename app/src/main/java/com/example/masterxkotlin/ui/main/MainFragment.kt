package com.example.masterxkotlin.ui.main

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseApplication
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentMainBinding
import com.example.masterxkotlin.di.ViewModelFactory
import javax.inject.Inject

class MainFragment: BaseFragment<FragmentMainBinding>(), MainFragmentNavigator {

    @Inject
    lateinit var factory: ViewModelFactory

    private val TAG = "MainFragment"

    lateinit var mViewModel: MainFragmentViewModel
    private var words = mutableListOf<WordsPair>()
    private var curIndex = -1
    private var isReversed = false

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
            scaleAnimate(mViewDataBinding.cardFirst, true)
            Handler(Looper.getMainLooper()).postDelayed({
                  setWords()
            }, 150L)
        } else {
            mViewDataBinding.btnShow.text = "NEXT"
            scaleAnimate(mViewDataBinding.cardSecond, false)
        }
    }

    override fun onReversePressed() {
        rotateAnimate()

        isReversed = !isReversed

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

    private fun scaleAnimate(view: View, isFirstCard: Boolean) {
        if (isFirstCard){
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0f)
            val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f)

            ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY, alpha).apply {
                    interpolator = OvershootInterpolator()
                    duration = 300L
                    start()
            }
        }

        val scaleX1 = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1f)
        val scaleY1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1f)
        val alpha1 = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)

        ObjectAnimator.ofPropertyValuesHolder(view, scaleX1, scaleY1, alpha1).apply {
            interpolator = OvershootInterpolator()
            startDelay = 300L
            start()
        }

        if (isFirstCard.not()) {
            Handler(Looper.getMainLooper()).postDelayed({
                mViewDataBinding.cardSecond.visibility = View.VISIBLE
            }, 300L)
        }
    }

    private fun rotateAnimate() {
        val rotate = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f)
        ObjectAnimator.ofPropertyValuesHolder(mViewDataBinding.ivReverse, rotate).apply {
            interpolator = LinearInterpolator()
            duration = 500L
            start()
        }

        var drawable = R.drawable.ic_reverse

        if (isReversed.not())
            drawable = R.drawable.ic_reverse_red

        Handler(Looper.getMainLooper()).postDelayed({
            mViewDataBinding.ivReverse.setImageResource(drawable)
        }, 500L)
    }

    private fun animat(view: View, isFirstCard: Boolean, scaleX: Float, scaleY: Float, mInterpolator: Interpolator) {
        val scaleX1 = PropertyValuesHolder.ofFloat(View.SCALE_X, scaleX, scaleY)
        val scaleY1 = PropertyValuesHolder.ofFloat(View.SCALE_Y, scaleX, scaleY)
        val alpha1 = PropertyValuesHolder.ofFloat(View.ALPHA, scaleX, scaleY)

        ObjectAnimator.ofPropertyValuesHolder(view, scaleX1, scaleY1, alpha1).apply {
            interpolator = mInterpolator
            startDelay = 300L
            start()
        }

    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun getLayoutId() = R.layout.fragment_main
}
