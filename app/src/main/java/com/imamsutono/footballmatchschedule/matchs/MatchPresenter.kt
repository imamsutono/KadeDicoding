package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.repository.MatchRepositoryCallback

class MatchPresenter(private val view: MatchView, private val matchRepository: MatchRepository) {

    fun getPrevMatch(id: String) {
        view.showLoading()
        matchRepository.getPrevMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }

    fun getNextMatch(id: String) {
        view.showLoading()
        matchRepository.getNextMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
            }

            override fun onDataError() {
                view.onDataError()
            }
        })
        view.hideLoading()
    }
}