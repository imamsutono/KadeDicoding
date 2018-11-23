package com.imamsutono.footballmatchschedule.network

import com.imamsutono.footballmatchschedule.model.MatchDetailResponse
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {
    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") id: String): Call<MatchDetailResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") id: String?): Call<TeamResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Call<MatchResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<MatchResponse>
}