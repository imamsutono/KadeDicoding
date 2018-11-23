package com.imamsutono.footballmatchschedule.matchs

import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.repository.MatchRepository
import com.imamsutono.footballmatchschedule.repository.MatchRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var matchResponse: MatchResponse

    @Mock
    private lateinit var matchPresenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        matchPresenter = MatchPresenter(view, matchRepository)
    }

    @Test
    fun getMatchLoadedTest() {
        val id = "4328"

        matchPresenter.getPrevMatch(id)

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(matchRepository).getPrevMatch(eq(id), capture())
            firstValue.onDataLoaded(matchResponse)
        }

        verify(view).showLoading()
        verify(view).onDataLoaded(matchResponse)
        verify(view).hideLoading()
    }

    @Test
    fun getMatchErrorTest() {
        matchPresenter.getPrevMatch("")

        argumentCaptor<MatchRepositoryCallback<MatchResponse?>>().apply {

            verify(matchRepository).getPrevMatch(eq(""), capture())
            firstValue.onDataError()
        }

        verify(view).showLoading()
        verify(view).onDataError()
        verify(view).hideLoading()
    }
}