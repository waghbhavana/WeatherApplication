package com.bhavanawagh.weatherapplication.data.api

import com.bhavanawagh.weatherapplication.data.model.LatLonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {

    @GET("geo/1.0/direct")
    suspend fun getLatLonForCity(
        @Query("q") city : String,
        @Query("appid") appId : String
    ):Response<LatLonResponse>
}