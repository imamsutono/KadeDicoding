package com.imamsutono.footballmatchschedule.teamdetail.player

import com.imamsutono.footballmatchschedule.model.Player
import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.repository.PlayerRepositoryCallback

interface TeamPlayerView : PlayerRepositoryCallback<PlayerResponse?> {
    fun showLoading()
    fun hideLoading()
    fun showTeamPlayer(data: List<Player>?)
}