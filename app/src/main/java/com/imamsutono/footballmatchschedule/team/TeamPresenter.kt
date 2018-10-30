package com.imamsutono.footballmatchschedule.team

import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

class TeamPresenter(private val view: TeamView, private val teamRepository: TeamRepository) {

    fun getTeamList(league: String) {
        view.showLoading()

        teamRepository.getTeamList(league, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onTeamLoaded(data: TeamResponse?, team: String) {
                view.onTeamLoaded(data, team)
                view.hideLoading()
            }

            override fun onTeamError() {
                view.onTeamError()
                view.hideLoading()
            }

        })
    }

    fun searchTeam(teamName: String?) {
        view.showLoading()

        teamRepository.searchTeam(teamName, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onTeamLoaded(data: TeamResponse?, team: String) {
                view.onTeamLoaded(data, "")
                view.hideLoading()
            }

            override fun onTeamError() {
                view.onTeamError()
                view.hideLoading()
            }

        })
    }
}