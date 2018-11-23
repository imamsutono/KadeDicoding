package com.imamsutono.footballmatchschedule.team

import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

interface TeamView : TeamRepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeam(data: List<Team>?)
}