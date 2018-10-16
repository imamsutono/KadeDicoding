package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.MatchDetail
import com.imamsutono.footballmatchschedule.model.MatchDetailResponse
import com.imamsutono.footballmatchschedule.repository.DetailRepositoryCallback

interface DetailView : DetailRepositoryCallback<MatchDetailResponse> {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(datas: List<MatchDetail>?)
}