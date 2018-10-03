package com.imamsutono.footballmatchschedule.service

import com.imamsutono.footballmatchschedule.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(@Query("id") id: String): Call<MatchDetail>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") id: String?): Call<Team>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Call<MatchList>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<MatchList>
}