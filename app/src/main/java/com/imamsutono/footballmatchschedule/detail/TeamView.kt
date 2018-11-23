package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

interface TeamView : TeamRepositoryCallback<TeamResponse> {
    fun showTeamBadge(data: List<Team>?, team: String)
}