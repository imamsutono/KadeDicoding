package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("teams")
    val data: MutableList<TeamData>?
)

data class TeamData(
    @SerializedName("strTeamBadge")
    val badge: String?
)