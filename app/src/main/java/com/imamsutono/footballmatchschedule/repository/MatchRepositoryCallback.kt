package com.imamsutono.footballmatchschedule.repository

interface MatchRepositoryCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}