package com.bhavanawagh.weatherapplication.data.model
import com.google.gson.annotations.SerializedName
data class LatLon (

    @SerializedName("name") var name    : String,
    @SerializedName("lat") var lat     : Double,
    @SerializedName("lon") var lon     : Double,
    @SerializedName("country") var country : String,
    @SerializedName("state") var state   : String

)