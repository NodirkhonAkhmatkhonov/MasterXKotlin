package com.example.masterxkotlin

import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.masterxkotlin.base.BaseActivity
import com.example.masterxkotlin.databinding.ActivityMainBinding
import com.example.masterxkotlin.viewmodels.MyViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import soup.neumorphism.NeumorphButton

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var textView: NeumorphButton
    private lateinit var mViewModel: MyViewModel

    private lateinit var right_to_middle: Animation
    private lateinit var middle_to_left: Animation

    private var rightToMiddle = true

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout

    private fun initObservers() {
        mViewModel.words.observe(this, Observer {
            textView.text = "sadf"
        })
    }

    override fun initView(mViewDataBinding: ViewDataBinding?) {
        textView = findViewById(R.id.text_view)
        textView.setOnClickListener(this)

        initNavigationDrawer()

        initViewModel()

        initAnimation()

        initObservers()
    }

    private fun initNavigationDrawer() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun initAnimation() {
        right_to_middle = AnimationUtils.loadAnimation(textView.context, R.anim.right_to_middle)
        middle_to_left = AnimationUtils.loadAnimation(this, R.anim.middle_to_left)
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(MyViewModel::class.java)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.text_view -> {
                textView.startAnimation(middle_to_left)

                textView.postDelayed({
                    textView.text = rightToMiddle.toString()
                    textView.startAnimation(right_to_middle)
                }, 800)

                rightToMiddle = !rightToMiddle
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }

        return true
    }

    override fun getLayoutId() = R.layout.activity_main
}