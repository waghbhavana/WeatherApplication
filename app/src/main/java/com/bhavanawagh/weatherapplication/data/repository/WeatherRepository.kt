package com.bhavanawagh.weatherapplication.data.repository

import com.bhavanawagh.weatherapplication.data.api.NetworkServices
import com.bhavanawagh.weatherapplication.data.model.LatLon
import com.bhavanawagh.weatherapplication.data.model.WeatherResponse
import com.bhavanawagh.weatherapplication.di.WeatherApiKey
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val networkServices: NetworkServices,
    @WeatherApiKey private val apiKey: String
) {

    suspend fun getLatLonForCity(city: String): Response<List<LatLon>> {
        return networkServices.getLatLonForCity(city, apiKey)
    }

    suspend fun getWeatherForLatLon(lat: Double, lon: Double): Response<WeatherResponse> {
        return networkServices.getWeatherForLatLon(lat, lon, apiKey)

    }
}