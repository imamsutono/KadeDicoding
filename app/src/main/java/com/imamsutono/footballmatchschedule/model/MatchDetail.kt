package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class MatchDetailResponse(
    @SerializedName("events")
    val data: List<MatchDetail>?
)

data class MatchDetail(
    @SerializedName("dateEvent")
    val dateEvent: String?,

    @SerializedName("strTime")
    val timeEvent: String?,

    @SerializedName("strHomeTeam")
    val homeTeamName: String?,

    @SerializedName("strHomeGoalDetails")
    val homeGoals: String?,

    @SerializedName("strAwayTeam")
    val awayTeamName: String?,

    @SerializedName("strAwayGoalDetails")
    val awayGoals: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strHomeFormation")
    val homeFormation: String?,

    @SerializedName("strAwayFormation")
    val awayFormation: String?,

    @SerializedName("intHomeShots")
    val homeShots: String?,

    @SerializedName("intAwayShots")
    val awayShots: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    val homeGoalkeeper: String?,

    @SerializedName("strHomeLineupDefense")
    val homeDefense: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    val awayGoalkeeper: String?,

    @SerializedName("strAwayLineupDefense")
    val awayDefense: String?,

    @SerializedName("idHomeTeam")
    val idHomeTeam: String?,

    @SerializedName("idAwayTeam")
    val idAwayTeam: String?
)