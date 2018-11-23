package com.imamsutono.footballmatchschedule.teamdetail.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Player
import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepository
import com.imamsutono.footballmatchschedule.teamdetail.playerdetail.PlayerDetailActivity
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamPlayerFragment : Fragment(), TeamPlayerView {

    private lateinit var presenter: TeamPlayerPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var list: RecyclerView
    private var players: MutableList<Player> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team_player, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.swipe_player)

        list = view.find(R.id.rv_player)
        presenter = TeamPlayerPresenter(this, PlayerRepository())
        progressBar = view.find(R.id.progressbar_player)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = TeamPlayerAdapter(players) {
            context?.startActivity<PlayerDetailActivity>("id" to it.playerId)
        }

        val id = activity?.intent?.getStringExtra("id")
        presenter.getTeamPlayer(id)

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getTeamPlayer(id)
        }

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamPlayer(data: List<Player>?) {
        players.clear()

        if (data != null) {
            players.addAll(data)
        }

        list.adapter.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: PlayerResponse?) {
        showTeamPlayer(data?.data)
    }

    override fun onDataError() {
    }
}
