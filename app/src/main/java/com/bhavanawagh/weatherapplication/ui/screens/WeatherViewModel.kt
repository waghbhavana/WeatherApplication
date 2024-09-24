package com.bhavanawagh.weatherapplication.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor() :ViewModel(){

    fun getLatLonByCityName(city:String){

        println("City name is: $city")

    }

}