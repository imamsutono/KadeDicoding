package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class PrevMatch(
    @SerializedName("events")
    val data: MutableList<PrevMatchData>?
)

data class PrevMatchData(
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
    val awayScore: String?
)