package com.bhavanawagh.weatherapplication.data.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat") var lat: Double,
    @SerializedName("lon") val lon: Double
)