package com.example.masterxkotlin.ui.home

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseFragment
import com.example.masterxkotlin.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding>(), HomeFragmentNavigator {

    private lateinit var mViewModel: HomeFragmentViewModel

    override fun init(view: View) {
        mViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        mViewDataBinding.homefragmentmodel = mViewModel
        mViewDataBinding.lifecycleOwner = this
        mViewModel.navigator = this
    }

    override fun onPlaygroundPressed() {
        findNavController().navigate(R.id.action_homeFragment_to_mainFragment)
    }

    override fun onWordsPressed() {
        findNavController().navigate(R.id.action_homeFragment_to_wordsFragment)
    }

    override fun onExitPressed() {
        activity?.finish()
    }

    override fun getLayoutId() = R.layout.fragment_home
}