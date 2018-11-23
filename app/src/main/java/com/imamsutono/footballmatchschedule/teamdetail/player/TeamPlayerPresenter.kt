package com.imamsutono.footballmatchschedule.teamdetail.player

import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepository
import com.imamsutono.footballmatchschedule.repository.PlayerRepositoryCallback

class TeamPlayerPresenter(private val view: TeamPlayerView, private val repository: PlayerRepository) {

    fun getTeamPlayer(teamId: String?) {
        view.showLoading()
        repository.getTeamPlayer(teamId, object : PlayerRepositoryCallback<PlayerResponse?> {
            override fun onDataLoaded(data: PlayerResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }

        })
        view.hideLoading()
    }
}