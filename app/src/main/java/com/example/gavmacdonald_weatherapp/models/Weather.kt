package com.example.gavmacdonald_weatherapp.models
// TODO: Add weather icons to resource files
data class CurrentWeather(
    val weatherImg: Int,
    val condition: String,
    val temp: Double,
    val precip: String,
    var windDir: String,
    var windSpeed: Double
)

data class DailyForecast(
    val weatherImg: Int,
    val date: String,
    val condition: String,
    val highTemp: Double,
    val lowTemp: Double,
    val precipType: String,
    val precipAmount: Double,
    val precipChance: Int,
    val windDir: String,
    val windSpeed: Double,
    val humidity: Int
)