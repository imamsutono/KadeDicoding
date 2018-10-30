package com.imamsutono.footballmatchschedule.repository

import com.imamsutono.footballmatchschedule.model.PlayerResponse
import com.imamsutono.footballmatchschedule.network.ApiRepository
import com.imamsutono.footballmatchschedule.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository {

    fun getTeamPlayer(id: String?, callback: PlayerRepositoryCallback<PlayerResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeamPlayer(id)
                .enqueue(object : Callback<PlayerResponse?> {
                    override fun onFailure(call: Call<PlayerResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<PlayerResponse?>?, response: Response<PlayerResponse?>?) {
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

    fun getPlayerDetail(id: String?, callback: PlayerRepositoryCallback<PlayerResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getPlayerDetail(id)
                .enqueue(object : Callback<PlayerResponse?> {
                    override fun onFailure(call: Call<PlayerResponse?>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<PlayerResponse?>?, response: Response<PlayerResponse?>?) {
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