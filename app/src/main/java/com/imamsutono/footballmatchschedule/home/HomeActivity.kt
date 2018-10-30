package com.imamsutono.footballmatchschedule.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.favorites.FavoritesFragment
import com.imamsutono.footballmatchschedule.matchs.MatchsFragment
import com.imamsutono.footballmatchschedule.team.TeamFragment
import org.jetbrains.anko.find

class HomeActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.tab_item_matchs -> {
                toolbar.title = "Matches"
                val matchsFragment = MatchsFragment.newInstance()
                openFragment(matchsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_item_teams -> {
                toolbar.title = "Teams"
                val teamsFragment = TeamFragment.newInstance()
                openFragment(teamsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_item_favorites -> {
                toolbar.title = "Favorites"
                val favoritesFragment = FavoritesFragment.newInstance()
                openFragment(favoritesFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_home, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = find(R.id.bottom_navigation_home)

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        bottomNavigation.selectedItemId = R.id.tab_item_matchs
    }
}
