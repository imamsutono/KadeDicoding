package com.imamsutono.footballmatchschedule.favorites.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.db.FavoriteTeams
import com.imamsutono.footballmatchschedule.db.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find

class FavoriteTeamsFragment : Fragment() {

    private val favorites: MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list = view?.find<RecyclerView>(R.id.rv_favorite_teams)
        val swipe = view?.find<SwipeRefreshLayout>(R.id.swipe_favorite_teams)

        adapter = FavoriteTeamsAdapter(favorites)
        list?.layoutManager = LinearLayoutManager(context)
        list?.adapter = adapter

        swipe?.setOnRefreshListener {
            swipe.isRefreshing = false
            showFavorite()
        }

        showFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_teams, container, false)
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAMS)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            favorites.clear()
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
