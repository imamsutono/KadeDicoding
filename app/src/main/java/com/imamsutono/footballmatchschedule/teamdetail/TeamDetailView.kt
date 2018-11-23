package com.imamsutono.footballmatchschedule.teamdetail

import com.imamsutono.footballmatchschedule.model.Team
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

interface TeamDetailView : TeamRepositoryCallback<TeamResponse> {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>?)
}