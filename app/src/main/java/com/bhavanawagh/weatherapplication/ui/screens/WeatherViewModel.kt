package com.bhavanawagh.weatherapplication.ui.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavanawagh.weatherapplication.data.model.WeatherResponse
import com.bhavanawagh.weatherapplication.data.repository.WeatherRepository
import com.bhavanawagh.weatherapplication.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :
    ViewModel() {


    private val _weatherResponse = MutableLiveData<NetworkResponse<WeatherResponse>>()
    val weatherResponse: MutableLiveData<NetworkResponse<WeatherResponse>> = _weatherResponse
   //Get lan-lon for user entered city name
    fun getLatLonByCityName(city: String) {
        viewModelScope.launch {
            // Here If I would have more time Instead of getting lat lon of entered city I would have get list of cities getting from api and
            // should have shown dropdown under search bar, and on user selection I could get lat lon for that city
            try {
                _weatherResponse.value = NetworkResponse.Loading
                val response = repository.getLatLonForCity(city)
                if (response.isSuccessful) {
                    if (response.body()?.size == 0) {
                        _weatherResponse.value =
                            NetworkResponse.Error("Please enter valid city name!")
                    } else {
                        val lat: Double? = response.body()?.get(0)?.lat
                        val lon: Double? = response.body()?.get(0)?.lon
                        if (lat != null && lon != null) {
                            getWeatherFromLatLon(lat, lon)
                        } else {
                            _weatherResponse.value = NetworkResponse.Error("Fail to load data!")
                        }
                    }
                } else {
                    _weatherResponse.value = NetworkResponse.Error("Please enter valid city name!")
                }

            } catch (_: Exception) {
            }

        }

    }

    //  Get weather details for lat lon for that city
    private suspend fun getWeatherFromLatLon(lat: Double, lon: Double) {
        try {
            val weatherResult = repository.getWeatherForLatLon(lat, lon)

            if (weatherResult.isSuccessful) {
                weatherResult.body()?.let { it ->
                    _weatherResponse.value = NetworkResponse.Success(it)
                }
            } else {
                _weatherResponse.value = NetworkResponse.Error("Fail to load data!")
            }
        } catch (_: Exception) {
        }

    }
}

