package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val data: List<Match>?,

    @SerializedName("event")
    val search: List<Match>?
)

data class Match(
    @SerializedName("idEvent")
    val idEvent: String?,

    @SerializedName("dateEvent")
    val dateEvent: String?,

    @SerializedName("strHomeTeam")
    val homeTeam: String?,

    @SerializedName("strAwayTeam")
    val awayTeam: String?,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?,

    @SerializedName("strTime")
    val timeEvent: String?
)