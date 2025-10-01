package com.example.gavmacdonald_weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gavmacdonald_weatherapp.models.CurrentWeather
import com.example.gavmacdonald_weatherapp.models.DailyForecast

class MainViewModel : ViewModel() {
    // Placeholder data for current weather and forecasts.
    // Will be replaced with API calls later.
    var currentWeather: CurrentWeather = CurrentWeather(
        conditionId = 1,
        condition = "Sunny",
        temp = 22.5,
        precip = "0mm",
        windDir = "NW",
        windSpeed = 12.0
    )
    var dailyForecasts: List<DailyForecast> = listOf(
        DailyForecast(
            date = "2025-09-19",
            conditionId = 1,
            condition = "Sunny",
            highTemp = 25.0,
            lowTemp = 15.0,
            precipType = "None",
            precipAmount = 0.0,
            precipChance = 0,
            windDir = "NW",
            windSpeed = 10.0,
            humidity = 50
        ),
        DailyForecast(
            date = "2025-09-20",
            conditionId = 2,
            condition = "Cloudy",
            highTemp = 20.0,
            lowTemp = 14.0,
            precipType = "Rain",
            precipAmount = 5.0,
            precipChance = 60,
            windDir = "E",
            windSpeed = 8.0,
            humidity = 70
        ),
        DailyForecast(
            date = "2025-09-21",
            conditionId = 3,
            condition = "Rain",
            highTemp = 18.0,
            lowTemp = 12.0,
            precipType = "Rain",
            precipAmount = 10.0,
            precipChance = 80,
            windDir = "S",
            windSpeed = 15.0,
            humidity = 85
        )
    )
}
