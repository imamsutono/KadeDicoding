package com.imamsutono.footballmatchschedule.teamdetail.playerdetail

import com.imamsutono.footballmatchschedule.model.Player
import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepositoryCallback

interface PlayerDetailView : PlayerRepositoryCallback<PlayerResponse> {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Player>?)
}