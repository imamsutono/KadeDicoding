package com.imamsutono.footballmatchschedule.teamdetail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.imamsutono.footballmatchschedule.teamdetail.overview.TeamOverviewFragment
import com.imamsutono.footballmatchschedule.teamdetail.player.TeamPlayerFragment

class TeamPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> TeamOverviewFragment()
            else -> TeamPlayerFragment()
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Overview"
            else -> "Player"
        }
    }
}