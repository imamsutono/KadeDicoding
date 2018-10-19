package com.imamsutono.footballmatchschedule.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar
import com.imamsutono.footballmatchschedule.R
import org.jetbrains.anko.find

class HomeActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_1 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_2 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_3 -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = find(R.id.bottom_navigation_home)

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }
}
