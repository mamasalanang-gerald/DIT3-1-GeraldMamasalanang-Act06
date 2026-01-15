package com.example.apiconnectapp.network

import com.example.apiconnectapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface defining the OpenWeatherMap API endpoints.
 */
interface WeatherApiService {
    
    /**
     * Fetches weather data for a specified city.
     * 
     * @param cityName The name of the city to get weather for
     * @param apiKey The OpenWeatherMap API key
     * @param units The unit system for temperature (default: metric for Celsius)
     * @return A Call object wrapping the WeatherResponse
     */
    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>
}
