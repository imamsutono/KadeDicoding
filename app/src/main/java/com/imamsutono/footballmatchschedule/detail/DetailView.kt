package com.imamsutono.footballmatchschedule.detail

import com.imamsutono.footballmatchschedule.model.MatchDetailData

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(datas: List<MatchDetailData>)
}