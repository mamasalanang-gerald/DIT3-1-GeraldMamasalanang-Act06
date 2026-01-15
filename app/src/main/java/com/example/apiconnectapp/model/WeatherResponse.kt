package com.example.apiconnectapp.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the weather API response from OpenWeatherMap.
 * Uses Gson annotations to map JSON field names to Kotlin properties.
 */
data class WeatherResponse(
    @SerializedName("name")
    val cityName: String,
    
    @SerializedName("main")
    val main: MainWeather,
    
    @SerializedName("weather")
    val weather: List<WeatherDescription>
)
