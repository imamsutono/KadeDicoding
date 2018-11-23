package com.imamsutono.footballmatchschedule.repository

interface DetailRepositoryCallback<T> {

    fun onDataLoaded(data: T?)
    fun onDataError()
}