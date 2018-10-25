package com.imamsutono.footballmatchschedule.team

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
import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.teamdetail.TeamDetailActivity
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamFragment : Fragment(), TeamView {

    private lateinit var presenter: TeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var list: RecyclerView
    private var teams: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.swipe_team)

        list = view.find(R.id.rv_team)
        presenter = TeamPresenter(this, TeamRepository())
        progressBar = view.find(R.id.progressbar_team)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = TeamAdapter(teams) {
            context?.startActivity<TeamDetailActivity>("id" to it.teamId)
        }

        presenter.getTeamList("English Premier League")

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getTeamList("English Premier League")
        }

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeam(data: List<Team>?) {
        teams.clear()
        if (data != null) {
            teams.addAll(data)
        }
        list.adapter.notifyDataSetChanged()
    }

    override fun onTeamLoaded(data: TeamResponse?, team: String) {
        showTeam(data?.data)
    }

    override fun onTeamError() {
    }

    companion object {
        fun newInstance(): TeamFragment = TeamFragment()
    }
}
