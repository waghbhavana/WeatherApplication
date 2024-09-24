package com.bhavanawagh.weatherapplication.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavanawagh.weatherapplication.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) :ViewModel(){

    fun getLatLonByCityName(city:String){

        println("City name is: $city")
        viewModelScope.launch {
           val response=repository.getLatLonForCity(city)

            println("response  is: $response")
        }


    }

}