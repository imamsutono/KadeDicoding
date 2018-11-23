package com.imamsutono.footballmatchschedule.repository

interface TeamRepositoryCallback<T> {

    fun onTeamLoaded(data: T?, team: String)
    fun onTeamError()
}