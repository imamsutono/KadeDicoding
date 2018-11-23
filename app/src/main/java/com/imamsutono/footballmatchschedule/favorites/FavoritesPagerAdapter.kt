package com.imamsutono.footballmatchschedule.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.imamsutono.footballmatchschedule.favorites.matchs.FavoriteMatchsFragment
import com.imamsutono.footballmatchschedule.favorites.teams.FavoriteTeamsFragment

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FavoriteMatchsFragment()
            else -> FavoriteTeamsFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Matches"
            else -> "Teams"
        }
    }
}