package com.bhavanawagh.weatherapplication.data.api

import com.bhavanawagh.weatherapplication.data.model.LatLon
import com.bhavanawagh.weatherapplication.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {

    //To get lat lon for user entered city
    @GET("geo/1.0/direct")
    suspend fun getLatLonForCity(
        @Query("q") city : String,
        @Query("appid") appId : String
    ):Response<List<LatLon>>

    //To get weather for selected lat lon
    @GET("data/2.5/weather")
    suspend fun getWeatherForLatLon(
        @Query("lat") lat : Double,
        @Query("lon") lon : Double,
        @Query("appid") appId : String
    ):Response<WeatherResponse>
}