package com.imamsutono.footballmatchschedule.repository

import com.imamsutono.footballmatchschedule.model.TeamResponse
import com.imamsutono.footballmatchschedule.network.ApiRepository
import com.imamsutono.footballmatchschedule.network.MyRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamRepository {

    fun getTeamBadge(id: String?, team: String, callback: TeamRepositoryCallback<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeamDetail(id)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onTeamError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onTeamLoaded(it.body(), team)
                            } else {
                                callback.onTeamError()
                            }
                        }
                    }

                })
    }

    fun getTeamList(league: String?, callback: TeamRepositoryCallback<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeamList(league)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onTeamError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onTeamLoaded(it.body(), "")
                            } else {
                                callback.onTeamError()
                            }
                        }
                    }

                })
    }

    fun searchTeam(teamName: String?, callback: TeamRepositoryCallback<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .searchTeam(teamName)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onTeamError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onTeamLoaded(it.body(), "")
                            } else {
                                callback.onTeamError()
                            }
                        }
                    }

                })
    }

    fun getTeamDetail(id: String?, callback: TeamRepositoryCallback<TeamResponse?>) {
        MyRetrofit
                .createService(ApiRepository::class.java)
                .getTeamDetail(id)
                .enqueue(object : Callback<TeamResponse?> {
                    override fun onFailure(call: Call<TeamResponse?>?, t: Throwable?) {
                        callback.onTeamError()
                    }

                    override fun onResponse(call: Call<TeamResponse?>?, response: Response<TeamResponse?>?) {
                        response?.let {
                            if (it.isSuccessful) {
                                callback.onTeamLoaded(it.body(), "")
                            } else {
                                callback.onTeamError()
                            }
                        }
                    }

                })
    }
}