package com.wonddak.study1.API

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface upbitAPI {
    @GET("market/all/")
    fun getlist(): Call<upbitCoin>
}

object upBitClient {
    var api: upbitAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.upbit.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(upbitAPI::class.java)
    }
}