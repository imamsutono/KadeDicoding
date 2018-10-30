package com.imamsutono.footballmatchschedule.repository

interface PlayerRepositoryCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}