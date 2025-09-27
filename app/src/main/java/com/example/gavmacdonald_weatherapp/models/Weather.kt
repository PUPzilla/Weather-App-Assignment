package com.example.gavmacdonald_weatherapp.models

import com.example.gavmacdonald_weatherapp.R

data class CurrentWeather(
    var conditionId: Int,
    val condition: String,
    val temp: Double,
    val precip: String,
    var windDir: String,
    var windSpeed: Double
)

data class DailyForecast(
    var conditionId: Int,
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

// Array of drawable resource IDs
private val conditionIcons = mapOf(
    1 to R.drawable.rounded_sunny_24,
    2 to R.drawable.rounded_cloud_24,
    3 to R.drawable.rounded_rainy_24,
    4 to R.drawable.rounded_rainy_heavy_24,
    5 to R.drawable.rounded_foggy_24,
    6 to R.drawable.rounded_thunderstorm_24,
    7 to R.drawable.rounded_weather_snowy_24,
)

fun loadIcon(conditionId: Int): Int {
    return conditionIcons[conditionId] ?: R.drawable.rounded_error_24
}