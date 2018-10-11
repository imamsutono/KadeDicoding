package com.imamsutono.footballmatchschedule.matchs

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.detail.DetailActivity
import com.imamsutono.footballmatchschedule.model.MatchData
import com.imamsutono.footballmatchschedule.model.MatchList
import com.imamsutono.footballmatchschedule.service.ServiceGenerator
import com.imamsutono.footballmatchschedule.service.ServiceInterface
import com.imamsutono.footballmatchschedule.util.invisible
import kotlinx.android.synthetic.main.fragment_prev_match.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_prev_match, container, false)
        val swipe = view.find<SwipeRefreshLayout>(R.id.prev_match_swipe_refresh)

        getPrevMatch(activity, view)

        swipe.setOnRefreshListener {
            swipe.isRefreshing = false
            getPrevMatch(activity, view)
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun getPrevMatch(context: Context?, view: View) {
        val config: ServiceInterface = ServiceGenerator.createBase().create(ServiceInterface::class.java)
        val leagueId = "4328" // English Premier League
        val call: Call<MatchList> = config.getPrevMatch(leagueId)

        call.enqueue(object: Callback<MatchList> {
            override fun onFailure(call: Call<MatchList>, t: Throwable) {
                t.message?.let { error(it) }
            }

            override fun onResponse(call: Call<MatchList>, response: Response<MatchList>) {
                if (response.code() == 200) {
                    val resp = response.body()
                    val datas: MutableList<MatchData> = mutableListOf()
                    val list = view.find<RecyclerView>(R.id.prev_match_list)
                    val progressBar = view.find<ProgressBar>(R.id.next_match_progressbar)

                    resp?.data?.let { datas.addAll(it) }

                    list.layoutManager = LinearLayoutManager(context)
                    list.adapter = MatchAdapter(datas) {
                        context?.startActivity<DetailActivity>("id" to it.idEvent)
                    }
                    progressBar.invisible()
                }
            }
        })
    }
}
