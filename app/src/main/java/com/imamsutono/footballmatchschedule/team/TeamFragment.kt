package com.imamsutono.footballmatchschedule.team

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*

import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.teamdetail.TeamDetailActivity
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamFragment : Fragment(), TeamView {

    private lateinit var presenter: TeamPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var list: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var league: String
    private var teams: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_team, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.swipe_team)

        spinner = view.find(R.id.spinner_league)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        swipeRefresh = view.find(R.id.swipe_team)
        list = view.find(R.id.rv_team)
        presenter = TeamPresenter(this, TeamRepository())
        progressBar = view.find(R.id.progressbar_team)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = TeamAdapter(teams) {
            context?.startActivity<TeamDetailActivity>("id" to it.teamId)
        }

        presenter.getTeamList("English Premier League")

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                league = spinner.selectedItem.toString()
                presenter.getTeamList(league)
            }
        }

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getTeamList("English Premier League")
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = "Search Team..."

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                searchView.isIconified = false
                searchView.requestFocusFromTouch()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                searchView.setQuery("", true)
                return true
            }

        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = false

            override fun onQueryTextChange(queryText: String?): Boolean {
                if (queryText != "") {
                    spinner.invisible()
                    presenter.searchTeam(queryText)
                } else {
                    spinner.visible()
                }

                return true
            }

        })

        searchItem.actionView = searchView
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeam(data: List<Team>?) {
        swipeRefresh.isRefreshing = false
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
