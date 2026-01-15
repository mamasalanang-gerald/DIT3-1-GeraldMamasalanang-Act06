package com.example.apiconnectapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconnectapp.adapter.WeatherAdapter
import com.example.apiconnectapp.config.ConfigManager
import com.example.apiconnectapp.model.WeatherResponse
import com.example.apiconnectapp.network.RetrofitClient
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Main activity for the APIConnectApp.
 * Handles weather search, API calls, and result display.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var apiKey: String

    private lateinit var searchEditText: TextInputEditText
    private lateinit var searchButton: MaterialButton
    private lateinit var weatherRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize API key from configuration
        apiKey = ConfigManager.getApiKey(this)
        
        // Validate API key
        if (apiKey.isEmpty() || apiKey == "YOUR_API_KEY_HERE") {
            showError("Please configure your OpenWeatherMap API key in assets/config.properties")
            return
        }
        
        setupUI()
    }

    /**
     * Initializes UI components and sets up event listeners.
     */
    private fun setupUI() {
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        weatherRecyclerView = findViewById(R.id.weatherRecyclerView)
        progressBar = findViewById(R.id.progressBar)

        // Setup RecyclerView
        weatherAdapter = WeatherAdapter()
        weatherRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = weatherAdapter
        }

        // Setup search button click listener
        searchButton.setOnClickListener {
            performSearch()
        }

        // Setup keyboard search action
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    /**
     * Performs the weather search operation.
     */
    private fun performSearch() {
        val cityName = searchEditText.text?.toString()?.trim() ?: ""
        
        if (cityName.isEmpty()) {
            showError(getString(R.string.search_hint))
            return
        }

        // Hide keyboard
        hideKeyboard()

        // Check network connectivity first
        if (!isNetworkAvailable()) {
            showError(getString(R.string.no_internet))
            return
        }

        fetchWeather(cityName)
    }

    /**
     * Checks if the device has an active network connection.
     */
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /**
     * Fetches weather data from the API for the specified city.
     */
    private fun fetchWeather(cityName: String) {
        // Double-check API key before making request
        if (apiKey.isEmpty() || apiKey == "YOUR_API_KEY_HERE") {
            showError("API key not configured. Please add your OpenWeatherMap API key to assets/config.properties")
            return
        }
        
        showLoading(true)

        RetrofitClient.instance.getWeather(cityName, apiKey).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                showLoading(false)

                if (response.isSuccessful) {
                    response.body()?.let { weather ->
                        weatherAdapter.updateData(weather)
                    } ?: run {
                        showError(getString(R.string.error_fetching))
                    }
                } else {
                    when (response.code()) {
                        404 -> showError(getString(R.string.city_not_found))
                        401 -> showError("Invalid API key. Please check your OpenWeatherMap API key in assets/config.properties")
                        else -> showError(getString(R.string.error_fetching))
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showLoading(false)
                showError(getString(R.string.error_fetching))
            }
        })
    }

    /**
     * Shows or hides the loading indicator.
     */
    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    /**
     * Displays an error message using a Snackbar.
     */
    private fun showError(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Hides the soft keyboard.
     */
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
