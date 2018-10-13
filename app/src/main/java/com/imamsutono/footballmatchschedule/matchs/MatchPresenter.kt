package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.service.ServiceGenerator
import com.imamsutono.footballmatchschedule.service.ServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(private val matchView: MatchView) {

    fun getMatch(match: String) {
        val config: ServiceInterface = ServiceGenerator.createBase().create(ServiceInterface::class.java)
        val leagueId = "4328" // English Premier League
        val call: Call<MatchResponse> = when(match) {
            "next" -> config.getNextMatch(leagueId)
            else -> config.getPrevMatch(leagueId)
        }

        matchView.showLoading()

        call.enqueue(object: Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                t.message?.let { error(it) }
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.code() == 200) {
                    val resp = response.body()

                    resp?.data?.let {
                        matchView.showMatchs(it)
                    }

                    matchView.hideLoading()
                }
            }

        })
    }
}