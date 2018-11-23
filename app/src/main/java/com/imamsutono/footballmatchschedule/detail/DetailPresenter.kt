package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.*
import com.imamsutono.footballmatchschedule.repository.DetailRepository
import com.imamsutono.footballmatchschedule.repository.DetailRepositoryCallback
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback

class DetailPresenter(
        private val view: DetailView,
        private val teamView: TeamView,
        private val detailRepository: DetailRepository,
        private val teamRepository: TeamRepository
) {

    fun getMatchDetail(id: String) {
        view.showLoading()
        detailRepository.getMatchDetail(id, object : DetailRepositoryCallback<MatchDetailResponse?> {
            override fun onDataLoaded(data: MatchDetailResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }

    fun getTeamBadge(id: String?, team: String) {
        view.showLoading()
        teamRepository.getTeamBadge(id, team, object : TeamRepositoryCallback<TeamResponse?> {
            override fun onTeamLoaded(data: TeamResponse?, team: String) {
                teamView.onTeamLoaded(data, team)
            }

            override fun onTeamError() {
                teamView.onTeamError()
            }
        })
        view.hideLoading()
    }
}