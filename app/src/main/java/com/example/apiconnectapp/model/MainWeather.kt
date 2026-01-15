package com.example.apiconnectapp.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the main weather metrics from the API response.
 */
data class MainWeather(
    @SerializedName("temp")
    val temperature: Double,
    
    @SerializedName("humidity")
    val humidity: Int,
    
    @SerializedName("feels_like")
    val feelsLike: Double
)
