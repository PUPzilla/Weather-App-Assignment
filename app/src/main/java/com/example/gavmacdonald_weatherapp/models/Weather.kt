package com.example.gavmacdonald_weatherapp.models

import com.example.gavmacdonald_weatherapp.R
class Weather {
    data class CurrentWeather(
        var conditionId: Int = 0,
        val condition: String,
        val temp: Double,
        val feelTemp: Int,
        val precip: String,
        var windDir: String,
        var windSpeed: Double
    )

    data class DailyForecast(
        var conditionId: Int = 0,
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
}

// Map of condition IDs to corresponding icons of drawable resources
private val conditionIcons = mapOf(
    1 to R.drawable.rounded_sunny_24,           // 1 Sunny
    2 to R.drawable.rounded_cloud_24,           // 2 Cloudy
    3 to R.drawable.rounded_rainy_24,           // 3 Rain
    4 to R.drawable.rounded_rainy_heavy_24,     // 4 Heavy Rain
    5 to R.drawable.rounded_foggy_24,           // 5 Fog
    6 to R.drawable.rounded_thunderstorm_24,    // 6 Thunder
    7 to R.drawable.rounded_weather_snowy_24,   // 7 Snow
)
fun loadIcon(condition: Int ): Int {
    return conditionIcons[condition] ?: R.drawable.rounded_error_24
}