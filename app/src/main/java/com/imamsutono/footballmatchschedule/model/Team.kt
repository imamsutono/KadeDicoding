package com.imamsutono.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val data: MutableList<Team>?
)

data class Team(
    @SerializedName("strTeamBadge")
    val badge: String?
)