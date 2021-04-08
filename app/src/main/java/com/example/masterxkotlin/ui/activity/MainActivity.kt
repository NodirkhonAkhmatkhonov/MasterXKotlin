package com.example.masterxkotlin.ui.activity

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.masterxkotlin.R
import com.example.masterxkotlin.base.BaseActivity
import com.example.masterxkotlin.databinding.ActivityMainBinding
import com.example.masterxkotlin.viewmodels.SharedViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(){

    private lateinit var mViewModel: SharedViewModel

    override fun initView(mViewDataBinding: ViewDataBinding?) {
        mViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

    }

    override fun getLayoutId() = R.layout.activity_main
}