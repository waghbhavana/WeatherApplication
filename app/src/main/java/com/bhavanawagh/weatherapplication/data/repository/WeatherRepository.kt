package com.bhavanawagh.weatherapplication.data.repository

import com.bhavanawagh.weatherapplication.data.api.NetworkServices
import com.bhavanawagh.weatherapplication.di.WeatherApiKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val networkServices: NetworkServices,
                                          @WeatherApiKey  private val apiKey: String) {

     suspend fun getLatLonForCity(city:String) {
        networkServices.getLatLonForCity(city,apiKey)
     }
}