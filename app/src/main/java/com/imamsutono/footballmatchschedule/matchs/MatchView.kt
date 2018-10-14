package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.Match
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepositoryCallback

interface MatchView : MatchRepositoryCallback<MatchResponse> {
    fun showLoading()
    fun hideLoading()
    fun showMatchs(data: List<Match>)
}