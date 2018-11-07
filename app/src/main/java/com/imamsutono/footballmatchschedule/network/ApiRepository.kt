package com.imamsutono.footballmatchschedule.network

import com.imamsutono.footballmatchschedule.model.MatchDetailResponse
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {
    @GET("api/v1/json/1/lookupevent.php")
//    @GET("lookupevent.json")
    fun getMatchDetail(@Query("id") id: String): Call<MatchDetailResponse>

    @GET("api/v1/json/1/lookupteam.php")
//    @GET("lookupteam.json")
    fun getTeamDetail(@Query("id") id: String?): Call<TeamResponse>

    //    @GET("eventspastleague.json")
    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatch(@Query("id") id: String?): Call<MatchResponse>

    //    @GET("eventsnextleague.json")
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String?): Call<MatchResponse>

        @GET("api/v1/json/1/searchevents.php")
//    @GET("searchevents.json")
    fun searchMatch(@Query("e") eventName: String?): Call<MatchResponse>

    @GET("api/v1/json/1/search_all_teams.php")
//    @GET("search_all_teams.json")
    fun getTeamList(@Query("l") league: String?): Call<TeamResponse>

    @GET("api/v1/json/1/lookup_all_players.php")
//    @GET("lookup_all_players.json")
    fun getTeamPlayer(@Query("id") teamId: String?): Call<PlayerResponse>

    @GET("api/v1/json/1/lookupplayer.php")
//    @GET("lookupplayer.json")
    fun getPlayerDetail(@Query("id") teamId: String?): Call<PlayerResponse>

    @GET("api/v1/json/1/searchteams.php")
//    @GET("searchteams.json")
    fun searchTeam(@Query("t") teamName: String?): Call<TeamResponse>
}