package com.imamsutono.footballmatchschedule.teamdetail.playerdetail

import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepository
import com.imamsutono.footballmatchschedule.repository.PlayerRepositoryCallback

class PlayerDetailPresenter(private val view: PlayerDetailView, private val repository: PlayerRepository) {

    fun getPlayerDetail(id: String?) {
        view.showLoading()

        repository.getPlayerDetail(id, object : PlayerRepositoryCallback<PlayerResponse?> {
            override fun onDataLoaded(data: PlayerResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }

        })
    }
}