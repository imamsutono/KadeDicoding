package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.repository.MatchRepositoryCallback

class MatchPresenter(private val view: MatchView, private val matchRepository: MatchRepository) {

    fun getPrevMatch(id: String?) {
        view.showLoading()
        matchRepository.getPrevMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onPrevMatchLoaded(data: MatchResponse?) {
                view.onPrevMatchLoaded(data)
                view.hideLoading()
            }

            override fun onDataLoaded(data: MatchResponse?) {
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }

    fun getNextMatch(id: String) {
        view.showLoading()
        matchRepository.getNextMatch(id, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onPrevMatchLoaded(data: MatchResponse?) {
            }

            override fun onDataLoaded(data: MatchResponse?) {
                view.onDataLoaded(data)
                view.hideLoading()
            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoading()
            }
        })
    }

    fun searchMatch(eventName: String?) {
        view.showLoading()

        matchRepository.searchMatch(eventName, object : MatchRepositoryCallback<MatchResponse?> {
            override fun onPrevMatchLoaded(data: MatchResponse?) {
            }

            override fun onDataLoaded(data: MatchResponse?) {
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