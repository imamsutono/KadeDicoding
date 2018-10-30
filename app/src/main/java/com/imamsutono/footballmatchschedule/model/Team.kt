package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val data: MutableList<Team>?
)

data class Team(
    @SerializedName("idTeam")
    val teamId: String?,

    @SerializedName("strTeam")
    val teamName: String?,

    @SerializedName("strTeamBadge")
    val badge: String?,

    @SerializedName("intFormedYear")
    val teamFormedYear: String?,

    @SerializedName("strStadium")
    val teamStadium: String?,

    @SerializedName("strDescriptionEN")
    val teamDescription: String?
)