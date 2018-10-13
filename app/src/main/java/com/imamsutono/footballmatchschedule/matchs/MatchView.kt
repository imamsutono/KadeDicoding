package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchs(data: List<Match>)
}