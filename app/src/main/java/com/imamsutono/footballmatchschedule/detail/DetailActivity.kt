package com.imamsutono.footballmatchschedule.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.imamsutono.footballmatchschedule.R
import com.imamsutono.footballmatchschedule.model.MatchDetail
import com.imamsutono.footballmatchschedule.model.MatchDetailData
import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamData
import com.imamsutono.footballmatchschedule.service.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.lineup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val config: ServiceInterface = ServiceGenerator.createBase().create(ServiceInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getMatchDetail()
    }

    private fun getMatchDetail() {
        val idEvent = intent.getStringExtra("idEvent")
        val call: Call<MatchDetail> = config.getMatchDetail(idEvent)

        call.enqueue(object: Callback<MatchDetail> {
            override fun onFailure(call: Call<MatchDetail>, t: Throwable) {
                Log.e("failure", t.toString())
            }

            override fun onResponse(call: Call<MatchDetail>, response: Response<MatchDetail>) {
                if (response.code() == 200) {
                    val resp = response.body()
                    val datas: MutableList<MatchDetailData> = mutableListOf()

                    resp?.data?.let { datas.addAll(it) }
                    val data = datas[0]
                    val scores = if (data.homeScore != null) "${data.homeScore} vs ${data.awayScore}" else ""

                    home_team.text = data.homeTeamName
                    home_goal.text = data.homeGoals?.replace(";", "\n")

                    date_event.text = data.dateEvent
                    score.text = scores

                    away_team.text = data.awayTeamName
                    away_goal.text = data.awayGoals?.replace(";", "\n")

                    home_formation.text = data.homeFormation
                    away_formation.text = data.awayFormation
                    home_shots.text = data.homeShots
                    away_shots.text = data.awayShots

                    home_team_name.text = data.homeTeamName
                    home_goalkeeper.text = data.homeGoalkeeper
                    home_defense.text = data.homeDefense?.replace(";", "\n")
                    away_team_name.text = data.awayTeamName
                    away_goalkeeper.text = data.awayGoalkeeper
                    away_defense.text = data.awayDefense?.replace(";", "\n")

                    val homeBadge: ImageView = findViewById(R.id.home_badge)
                    val awayBadge: ImageView = findViewById(R.id.away_badge)

                    callTeam(data.idHomeTeam, homeBadge)
                    callTeam(data.idAwayTeam, awayBadge)
                }
            }

        })
    }

    private fun callTeam(teamId: String?, imgView: ImageView) {
        val callTeam: Call<Team> = config.getTeamDetail(teamId)

        callTeam.enqueue(object: Callback<Team> {
            override fun onFailure(call: Call<Team>, t: Throwable) {
                Log.e("callteam", t.toString())
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
