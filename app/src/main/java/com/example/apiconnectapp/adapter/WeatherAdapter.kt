package com.example.apiconnectapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconnectapp.R
import com.example.apiconnectapp.model.WeatherResponse

/**
 * RecyclerView adapter for displaying weather data.
 */
class WeatherAdapter(
    private val weatherList: MutableList<WeatherResponse> = mutableListOf()
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size

    /**
     * Updates the adapter with new weather data.
     * Clears existing data and adds the new weather response.
     */
    fun updateData(newWeather: WeatherResponse) {
        weatherList.clear()
        weatherList.add(newWeather)
        notifyDataSetChanged()
    }

    /**
     * Clears all weather data from the adapter.
     */
    fun clearData() {
        weatherList.clear()
        notifyDataSetChanged()
    }

    /**
     * ViewHolder for weather item views.
     */
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityNameText: TextView = itemView.findViewById(R.id.cityNameText)
        private val temperatureText: TextView = itemView.findViewById(R.id.temperatureText)
        private val weatherDescriptionText: TextView = itemView.findViewById(R.id.weatherDescriptionText)
        private val feelsLikeText: TextView = itemView.findViewById(R.id.feelsLikeText)
        private val humidityText: TextView = itemView.findViewById(R.id.humidityText)

        /**
         * Binds weather data to the item views.
         */
        fun bind(weather: WeatherResponse) {
            val context = itemView.context
            
            cityNameText.text = weather.cityName
            temperatureText.text = context.getString(
                R.string.temperature_format, 
                weather.main.temperature
            )
            
            // Get the first weather description if available
            val description = weather.weather.firstOrNull()?.description?.replaceFirstChar { 
                it.uppercase() 
            } ?: ""
            weatherDescriptionText.text = description
            
            feelsLikeText.text = context.getString(
                R.string.feels_like_format, 
                weather.main.feelsLike
            )
            humidityText.text = context.getString(
                R.string.humidity_format, 
                weather.main.humidity
            )
        }
    }
}
