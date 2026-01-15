# APIConnectApp – Activity 06

**Name:** Gerald Mamasalanang  
**Section:** DIT 3-1  
**Activity Title:** REST API Integration with Retrofit  
**Repository Name:** DIT3-1-GeraldMamasalanang-Act06  

---

## Project Description

This project is an Android app called **APIConnectApp** that fetches and displays real-time weather data from the **OpenWeatherMap REST API**.  
The app demonstrates networking capabilities using **Retrofit** for HTTP communication and **Gson** for JSON parsing.

The main features include:
- Search weather by **city name**
- Display **temperature**, **weather description**, and **conditions**
- **Network connectivity checking** before API calls
- Proper **error handling** for invalid cities and API issues
- Clean **Material Design UI** with ProgressBar loading states

---

## Setup Instructions

### 1. Get an OpenWeatherMap API Key

1. Go to [OpenWeatherMap API](https://openweathermap.org/api)
2. Sign up for a free account
3. Navigate to your API keys section
4. Copy your API key

> **Note:** New API keys can take up to **2 hours** to activate after creation.

### 2. Configure the API Key

1. Open `app/src/main/assets/config.properties`
2. Replace the placeholder with your actual API key:
   ```properties
   api_key=your_actual_api_key_here
   ```

### 3. Build and Run

1. Open the project in Android Studio
2. Build the project (Build → Make Project)
3. Run on device or emulator
4. Search for weather by entering a city name

---

## Project Architecture

```
app/src/main/java/com/example/apiconnectapp/
├── MainActivity.kt              # Main activity handling UI and API calls
├── adapter/
│   └── WeatherAdapter.kt        # RecyclerView adapter for weather display
├── config/
│   └── ConfigManager.kt         # API key configuration manager
├── model/
│   ├── WeatherResponse.kt       # Data class for API response
│   └── WeatherDescription.kt    # Data class for weather details
└── network/
    ├── RetrofitClient.kt        # Singleton Retrofit instance
    └── WeatherApiService.kt     # API interface definition
```

---

## Technologies Used

- **Kotlin** - Primary programming language
- **Retrofit 2** - Type-safe HTTP client for API calls
- **Gson** - JSON serialization/deserialization
- **Material Design** - Modern UI components
- **RecyclerView** - Efficient list display
- **ConnectivityManager** - Network status checking

---

## Troubleshooting

| Error | Solution |
|-------|----------|
| "Invalid API key" | Wait up to 2 hours for new keys to activate, or generate a new key |
| "Please configure your API key" | Add your API key to `assets/config.properties` |
| "No internet connection" | Check device/emulator network connectivity |
| "City not found" | Verify the city name spelling |

### Test Your API Key

Open this URL in your browser (replace with your key):
```
https://api.openweathermap.org/data/2.5/weather?q=London&appid=YOUR_API_KEY
```

If you see JSON weather data, your key is active. If you see a 401 error, wait for activation.

---

## Reflection

**1. How did you implement API integration using Retrofit?**  
I created a `WeatherApiService` interface with a `@GET` annotation to define the weather endpoint. The `RetrofitClient` singleton configures Retrofit with the base URL and Gson converter. API calls are made asynchronously using Retrofit's `enqueue()` method with callbacks for success and failure handling.

**2. What challenges did you face with network operations?**  
The main challenge was handling the API key activation delay - new OpenWeatherMap keys can take hours to become active. I also had to implement proper error handling for various HTTP status codes (401 for invalid key, 404 for city not found) and network connectivity checks before making requests.

**3. How could you improve the app in future versions?**  
Future improvements could include: caching weather data for offline access, adding a 5-day forecast feature, implementing location-based weather using GPS, adding weather icons, and using Kotlin Coroutines instead of callbacks for cleaner async code.

---

## How to Run

1. Clone this repository:
   ```bash
   git clone git@github.com:mamasalanang-gerald/DIT3-1-GeraldMamasalanang-Act06.git
   ```
2. Open in Android Studio
3. Add your OpenWeatherMap API key to `app/src/main/assets/config.properties`
4. Build and run on emulator or device
