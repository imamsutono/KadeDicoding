package com.imamsutono.footballmatchschedule

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.imamsutono.footballmatchschedule.favorites.FavoriteMatchsFragment
import com.imamsutono.footballmatchschedule.matchs.NextMatchFragment
import com.imamsutono.footballmatchschedule.matchs.PrevMatchFragment

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> PrevMatchFragment()
            1 -> NextMatchFragment()
            else -> FavoriteMatchsFragment()
        }
    }

    override fun getCount(): Int = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Last"
            1 -> "Next"
            else -> "Favorite"
        }
    }
}