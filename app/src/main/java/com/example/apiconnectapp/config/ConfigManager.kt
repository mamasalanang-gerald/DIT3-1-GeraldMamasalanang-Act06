package com.example.apiconnectapp.config

import android.content.Context
import java.io.IOException
import java.util.Properties

/**
 * Manages application configuration from assets/config.properties file.
 */
object ConfigManager {
    
    private var properties: Properties? = null
    
    /**
     * Loads configuration properties from assets/config.properties.
     * 
     * @param context Application context
     */
    private fun loadProperties(context: Context) {
        if (properties == null) {
            properties = Properties()
            try {
                val inputStream = context.assets.open("config.properties")
                properties?.load(inputStream)
                inputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    
    /**
     * Gets the OpenWeatherMap API key from configuration.
     * 
     * @param context Application context
     * @return API key string, or empty string if not found
     */
    fun getApiKey(context: Context): String {
        loadProperties(context)
        return properties?.getProperty("api_key", "") ?: ""
    }
}