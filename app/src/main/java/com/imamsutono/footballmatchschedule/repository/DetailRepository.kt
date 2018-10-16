package com.imamsutono.footballmatchschedule.repository

import com.imamsutono.footballmatchschedule.model.MatchDetailResponse
import com.imamsutono.footballmatchschedule.network.ApiRepository
import com.imamsutono.footballmatchschedule.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRepository {

    fun getMatchDetail(id: String, callback: DetailRepositoryCallback<MatchDetailResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getMatchDetail(id)
                .enqueue(object : Callback<MatchDetailResponse?> {
                    override fun onFailure(call: Call<MatchDetailResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchDetailResponse?>?, response: Response<MatchDetailResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }

                })
    }
}