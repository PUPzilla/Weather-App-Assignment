package com.example.gavmacdonald_weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gavmacdonald_weatherapp.models.CurrentWeather
import com.example.gavmacdonald_weatherapp.models.DailyForecast

class WeatherViewModel : ViewModel() {
    val currentWeather = CurrentWeather(
        conditionId = 1,
        condition = "Sunny",
        temp = 22.3,
        precip = "None",
        windDir = "SE",
        windSpeed = 11.0
    )

    val dailyForecasts = listOf(
        DailyForecast(1, "2025-09-01", "Sunny", 25.0, 12.0, "None", 0.0, 0, "SE", 10.0, 50),
        DailyForecast(2,"2025-09-02", "Cloudy", 25.0, 12.0, "Light Rain", 5.0, 20, "SE", 16.0, 40),
        DailyForecast(2, "2025-09-03", "Overcast", 20.0, 8.0, "Rain", 7.0, 60, "S", 19.0, 70)
    )
}