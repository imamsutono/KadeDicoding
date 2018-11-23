package com.imamsutono.footballmatchschedule.repository

import android.util.Log
import com.imamsutono.footballmatchschedule.model.MatchResponse
import com.imamsutono.footballmatchschedule.network.ApiRepository
import com.imamsutono.footballmatchschedule.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository {

    fun getPrevMatch(id: String?, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getPrevMatch(id)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onPrevMatchLoaded(it.body())
                                Log.d("prev match", it.body().toString())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }

                })
    }

    fun getNextMatch(id: String, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getNextMatch(id)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onDataLoaded(it.body())
                                Log.d("next match", it.body().toString())
                            } else {
                                callback.onDataError()
                            }
                        }
                    }

                })
    }

    fun searchMatch(eventName: String?, callback: MatchRepositoryCallback<MatchResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .searchMatch(eventName)
                .enqueue(object : Callback<MatchResponse?> {
                    override fun onFailure(call: Call<MatchResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<MatchResponse?>?, response: Response<MatchResponse?>?) {
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