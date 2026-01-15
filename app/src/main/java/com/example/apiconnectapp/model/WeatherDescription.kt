package com.example.apiconnectapp.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing weather description details from the API response.
 */
data class WeatherDescription(
    @SerializedName("main")
    val main: String,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("icon")
    val icon: String
)
