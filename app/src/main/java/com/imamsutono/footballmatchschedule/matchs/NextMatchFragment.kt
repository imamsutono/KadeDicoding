package com.imamsutono.footballmatchschedule.matchs

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
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import com.imamsutono.footballmatchschedule.model.Match
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class NextMatchFragment : Fragment(), MatchView {
    private lateinit var presenter: MatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var list: RecyclerView
    private var matchs: MutableList<Match> = mutableListOf()

    override fun onDataLoaded(data: MatchResponse?) {
        showMatchs(data?.data)
    }

    override fun onDataError() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_next_match, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.next_match_swipe_refresh)

        list = view.find(R.id.next_match_list)
        presenter = MatchPresenter(this, MatchRepository())
        progressBar = view.find(R.id.next_match_progressbar)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = MatchAdapter(matchs) {
            context?.startActivity<DetailActivity>("id" to it.idEvent)
        }

        presenter.getNextMatch("4328")

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getNextMatch("4328")
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

    override fun showMatchs(data: List<Match>?) {
        matchs.clear()
        if (data != null) {
            matchs.addAll(data)
        }
        list.adapter.notifyDataSetChanged()
    }
}
