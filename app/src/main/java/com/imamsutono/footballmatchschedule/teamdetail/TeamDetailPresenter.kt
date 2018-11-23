package com.imamsutono.footballmatchschedule.teamdetail

import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

class TeamDetailPresenter(private val view: TeamDetailView, private val teamRepository: TeamRepository) {

    fun getTeamDetail(id: String) {
        view.showLoading()

        teamRepository.getTeamDetail(id, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onTeamLoaded(data: TeamResponse?, team: String) {
                view.onTeamLoaded(data, "")
            }

            override fun onTeamError() {
                view.onTeamError()
            }

        })

        view.hideLoading()
    }
}