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
import com.imamsutono.footballmatchschedule.util.invisible
import com.imamsutono.footballmatchschedule.util.visible
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class PrevMatchFragment : Fragment(), MatchView {
    private lateinit var presenter: MatchPresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_prev_match, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.prev_match_swipe_refresh)

        presenter = MatchPresenter(this)
        progressBar = view.find(R.id.prev_match_progressbar)

        presenter.getMatch("prev")

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            presenter.getMatch("prev")
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

    override fun showMatchs(data: List<Match>) {
        val list = view?.find<RecyclerView>(R.id.prev_match_list)

        list?.layoutManager = LinearLayoutManager(context)
        list?.adapter = MatchAdapter(data) {
            context?.startActivity<DetailActivity>("id" to it.idEvent)
        }
    }
}
