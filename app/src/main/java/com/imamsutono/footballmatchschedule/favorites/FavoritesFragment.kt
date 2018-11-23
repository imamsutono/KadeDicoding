package com.imamsutono.footballmatchschedule.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imamsutono.footballmatchschedule.R
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = FavoritesPagerAdapter(childFragmentManager)
        viewpager_favorite.adapter = fragmentAdapter

        tabs_favorite.setupWithViewPager(viewpager_favorite)
    }

    companion object {
        fun newInstance(): FavoritesFragment = FavoritesFragment()
    }
}
