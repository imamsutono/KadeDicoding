package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.MatchDetailResponse
import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.repository.DetailRepository
import com.imamsutono.footballmatchschedule.repository.DetailRepositoryCallback
import com.imamsutono.footballmatchschedule.repository.TeamRepository
import com.imamsutono.footballmatchschedule.repository.TeamRepositoryCallback
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var teamView: TeamView

    @Mock
    private lateinit var matchDetailRepository: DetailRepository

    @Mock
    private lateinit var teamRepository: TeamRepository

    @Mock
    private lateinit var matchDetailResponse: MatchDetailResponse

    @Mock
    private lateinit var matchDetailPresenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        matchDetailPresenter = DetailPresenter(view, teamView, matchDetailRepository, teamRepository)
    }

    @Test
    fun getMatchLoadedTest() {
        val id = "441613"

        matchDetailPresenter.getMatchDetail(id)

        argumentCaptor<DetailRepositoryCallback<MatchDetailResponse?>>().apply {

            verify(matchDetailRepository).getMatchDetail(eq(id), capture())
            firstValue.onDataLoaded(matchDetailResponse)
        }

        verify(view).showLoading()
        verify(view).onDataLoaded(matchDetailResponse)
        verify(view).hideLoading()
    }

    @Test
    fun getMatchErrorTest() {
        matchDetailPresenter.getMatchDetail("")

        argumentCaptor<DetailRepositoryCallback<MatchDetailResponse?>>().apply {

            verify(matchDetailRepository).getMatchDetail(eq(""), capture())
            firstValue.onDataError()
        }

        verify(view).showLoading()
        verify(view).onDataError()
        verify(view).hideLoading()
    }

    @Test
    fun getTeamBadgeTest() {
        val id = "133604"

        matchDetailPresenter.getTeamBadge(id, "home")

        argumentCaptor<TeamRepositoryCallback<TeamResponse?>>().apply {
            verify(teamRepository).getTeamBadge(eq(id), eq("home"), capture())
        }
    }
}