package com.imamsutono.footballmatchschedule.network

import com.imamsutono.footballmatchschedule.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
//                .baseUrl("http://imamsutono.rumahkoding.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> createService(service: Class<T>): T {
        return initRetrofit().create(service)
    }
}