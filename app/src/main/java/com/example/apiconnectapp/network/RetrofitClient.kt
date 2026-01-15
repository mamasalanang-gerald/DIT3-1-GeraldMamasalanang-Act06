package com.example.apiconnectapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object providing a configured Retrofit instance for API calls.
 */
object RetrofitClient {
    
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    
    /**
     * Lazy-initialized WeatherApiService instance.
     * Creates the Retrofit client with Gson converter on first access.
     */
    val instance: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
