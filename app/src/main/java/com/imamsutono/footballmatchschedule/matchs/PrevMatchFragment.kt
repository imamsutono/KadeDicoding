package com.imamsutono.footballmatchschedule.matchs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import com.imamsutono.footballmatchschedule.model.Match
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class PrevMatchFragment : Fragment(), MatchView {
    private lateinit var presenter: MatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var list: RecyclerView
    private lateinit var spinner: Spinner
    private var matchs: MutableList<Match> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_prev_match, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.prev_match_swipe_refresh)
        val league = arrayOf("4328", "4329", "4331", "4332", "4334", "4335")
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        val spinnerPosition = spinnerAdapter.getPosition("English Premier League")

        spinner = view.find(R.id.spinner_league_prev_match)
        spinner.adapter = spinnerAdapter
        spinner.setSelection(spinnerPosition)

        list = view.find(R.id.rv_prev_match)
        presenter = MatchPresenter(this, MatchRepository())
        progressBar = view.find(R.id.prev_match_progressbar)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = MatchAdapter(matchs) {
            context?.startActivity<DetailActivity>("id" to it.idEvent)
        }

        presenter.getPrevMatch(league[0])

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                presenter.getPrevMatch(league[pos])
            }
        }

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getPrevMatch(league[0])
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun onPrevMatchLoaded(data: MatchResponse?) {
        showMatchs(data?.data)
    }

    override fun onDataLoaded(data: MatchResponse?) {
    }

    override fun onDataError() {
    }

    override fun showMatchs(data: List<Match>?) {
        matchs.clear()
        if (data != null) {
            matchs.addAll(data)
        }
        list.adapter.notifyDataSetChanged()
    }
}
