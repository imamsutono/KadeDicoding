package com.imamsutono.footballmatchschedule.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        fun createBase(): Retrofit {
            return Retrofit.Builder().baseUrl("https://www.thesportsdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}