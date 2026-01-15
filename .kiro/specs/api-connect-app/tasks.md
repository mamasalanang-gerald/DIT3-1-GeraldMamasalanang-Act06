# Implementation Plan: APIConnectApp

## Overview

Transform the existing NoteKeeperApp into APIConnectApp by removing the note-keeping functionality and implementing a weather data fetching app using Retrofit and OpenWeatherMap API. Tasks are ordered to build incrementally, with each step building on the previous.

## Tasks

- [x] 1. Clean up existing project and reconfigure for APIConnectApp
  - [x] 1.1 Remove existing note-related source files (Note.kt, NoteAdapter.kt, NoteDatabaseHelper.kt, AddNoteActivity.kt, EditNoteActivity.kt)
    - Delete files from app/src/main/java/com/example/notekeeperapp/
    - _Requirements: 1.3_
  - [x] 1.2 Update project configuration files
    - Update settings.gradle.kts to rename project to "APIConnectApp"
    - Update app/build.gradle.kts namespace to "com.example.apiconnectapp"
    - Add Retrofit, Gson, and Kotlin coroutines dependencies
    - _Requirements: 1.1, 1.3, 1.4_
  - [x] 1.3 Update AndroidManifest.xml
    - Add INTERNET permission
    - Remove AddNoteActivity and EditNoteActivity declarations
    - Update app label and theme references
    - _Requirements: 1.2_
  - [x] 1.4 Update resource files
    - Update strings.xml with new app name
    - Update themes.xml if needed
    - _Requirements: 1.3_

- [x] 2. Implement data models
  - [x] 2.1 Create WeatherResponse data class
    - Create data class with Gson annotations for cityName, main, and weather fields
    - _Requirements: 3.1, 3.2_
  - [x] 2.2 Create MainWeather data class
    - Create data class for temperature, humidity, and feelsLike fields
    - _Requirements: 3.1, 3.2_
  - [x] 2.3 Create WeatherDescription data class
    - Create data class for main, description, and icon fields
    - _Requirements: 3.1, 3.2_
  - [x] 2.4 Write property test for JSON round trip
    - **Property 1: JSON Parsing Round Trip**
    - **Validates: Requirements 3.3**

- [x] 3. Implement network layer
  - [x] 3.1 Create WeatherApiService interface
    - Define GET endpoint with @Query parameters for city, API key, and units
    - Return Call<WeatherResponse>
    - _Requirements: 2.1, 2.2, 2.3_
  - [x] 3.2 Create RetrofitClient singleton object
    - Configure Retrofit with base URL "https://api.openweathermap.org/data/2.5/"
    - Add GsonConverterFactory
    - Provide lazy-initialized WeatherApiService instance
    - _Requirements: 4.1, 4.2, 4.3, 2.4_

- [x] 4. Checkpoint - Verify network layer compiles
  - Ensure all data models and network components compile without errors

- [x] 5. Implement UI components
  - [x] 5.1 Create activity_main.xml layout
    - Add EditText for city name input
    - Add Button for search trigger
    - Add RecyclerView for weather display
    - Add ProgressBar (initially hidden)
    - _Requirements: 5.1, 5.2, 5.3, 5.4_
  - [x] 5.2 Create weather_item.xml layout
    - Design item layout showing city name, weather description, and temperature
    - _Requirements: 6.1, 6.2, 6.3_
  - [x] 5.3 Create WeatherAdapter class
    - Implement RecyclerView.Adapter with WeatherViewHolder
    - Implement updateData() and clearData() methods
    - Bind weather data to item views
    - _Requirements: 6.4_

- [ ] 6. Implement MainActivity functionality
  - [x] 6.1 Rewrite MainActivity with weather fetching logic
    - Initialize UI components
    - Set up RecyclerView with WeatherAdapter
    - Implement search button click listener
    - _Requirements: 7.1_
  - [x] 6.2 Implement network connectivity check
    - Create isNetworkAvailable() function using ConnectivityManager
    - Check connectivity before making API calls
    - _Requirements: 9.1, 9.2, 9.3_
  - [x] 6.3 Implement fetchWeather() function
    - Make Retrofit API call with city name
    - Handle success: update adapter with weather data
    - Handle failure: show appropriate error message
    - Manage ProgressBar visibility
    - _Requirements: 7.2, 7.3, 7.4_
  - [x] 6.4 Implement error handling with Snackbar
    - Show "No internet connection" when offline
    - Show "City not found" for 404 responses
    - Show generic error for other failures
    - Hide ProgressBar on any error
    - _Requirements: 8.1, 8.2, 8.3, 8.5_
  - [x] 6.5 Write property test for graceful error handling
    - **Property 2: Graceful Error Handling for Invalid Data**
    - **Validates: Requirements 8.4**

- [-] 7. Final checkpoint - Build and verify
  - Ensure the app builds successfully
  - Verify all components are wired together correctly
  - Ensure all tests pass, ask the user if questions arise

## Notes

- All tasks including property tests are required for comprehensive validation
- The app requires a valid OpenWeatherMap API key - user will need to obtain one from https://openweathermap.org/api
- Each task references specific requirements for traceability
- Property tests validate JSON parsing and error handling correctness
