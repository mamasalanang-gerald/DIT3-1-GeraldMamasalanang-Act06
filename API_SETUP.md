# APIConnectApp - API Setup Guide

A simple Android weather app that demonstrates API integration using OpenWeatherMap.

## Setup Instructions

### 1. Get an OpenWeatherMap API Key

1. Go to [OpenWeatherMap API](https://openweathermap.org/api)
2. Sign up for a free account
3. Navigate to your API keys section
4. Copy your API key

### 2. Configure the API Key

1. Open `app/src/main/assets/config.properties`
2. Replace `YOUR_API_KEY_HERE` with your actual API key:
   ```
   api_key=your_actual_api_key_here
   ```

### 3. Build and Run

1. Build the project in Android Studio
2. Run on device or emulator
3. Search for weather by city name

## Features

- Search weather by city name
- Display current temperature, weather description, and conditions
- Network connectivity checking
- Error handling for invalid cities and API issues
- Clean Material Design UI

## Troubleshooting

- **"Invalid API key" error**: Make sure you've replaced `YOUR_API_KEY_HERE` in `config.properties` with your actual API key
- **"Please configure your API key" error**: The config.properties file wasn't found or the API key is still the placeholder value
- **Network errors**: Check your internet connection and ensure the device/emulator has network access

## Current Fix Applied

The app now uses a configuration-based approach for API key management:

1. **ConfigManager**: Reads API key from `assets/config.properties`
2. **Validation**: Checks for valid API key on app startup
3. **Better Error Messages**: Clear instructions when API key is missing or invalid
4. **Security**: API key is not hardcoded in source code

To fix the "Invalid API key" error, simply follow the setup instructions above to add your OpenWeatherMap API key to the config file.