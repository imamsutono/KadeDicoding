package com.imamsutono.footballmatchschedule.detail

import android.widget.ImageView
import com.imamsutono.footballmatchschedule.model.*
import com.imamsutono.footballmatchschedule.service.ServiceGenerator
import com.imamsutono.footballmatchschedule.service.ServiceInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(private val view: DetailView) {
    private val config: ServiceInterface = ServiceGenerator.createBase().create(ServiceInterface::class.java)

    fun getMatchDetail(id: String) {
        val call: Call<MatchDetail> = config.getMatchDetail(id)

        view.showLoading()

        call.enqueue(object: Callback<MatchDetail> {
            override fun onFailure(call: Call<MatchDetail>, t: Throwable) {
                t.message?.let { error(it) }
            }

            override fun onResponse(call: Call<MatchDetail>, response: Response<MatchDetail>) {
                if (response.code() == 200) {
                    val resp = response.body()

                    resp?.data?.let {
                        view.showMatchDetail(it)
                    }
                    view.hideLoading()
                }
            }
        })
    }

    fun callTeam(teamId: String?, imgView: ImageView) {
        val callTeam: Call<Team> = config.getTeamDetail(teamId)

        callTeam.enqueue(object: Callback<Team> {
            override fun onFailure(call: Call<Team>, t: Throwable) {
                t.message?.let { error(it) }
            }

            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                if (response.code() == 200) {
                    val teamResponse = response.body()
                    val datas: MutableList<TeamData> = mutableListOf()

                    teamResponse?.data?.let { datas.addAll(it) }
                    val badge = datas[0].badge

                    Picasso.get().load(badge).into(imgView)
                }
            }

        })
    }
}