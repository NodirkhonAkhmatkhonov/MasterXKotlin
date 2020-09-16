package com.example.masterxkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.masterxkotlin.viewmodels.MyViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var textView: Button
    private lateinit var mViewModel: MyViewModel

    private lateinit var right_to_middle: Animation
    private lateinit var middle_to_left: Animation

    private var rightToMiddle = true

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView.setOnClickListener(this)

        initNavigationDrawer()

        initViewModel()

        initAnimation()
    }

    private fun initNavigationDrawer() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close)
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
        mViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

        mViewModel.getText().observe(this, Observer {
            textView.text = it
        })
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.textView -> {
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
}