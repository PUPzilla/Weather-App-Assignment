package com.example.gavmacdonald_weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gavmacdonald_weatherapp.models.Weather.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // Placeholder data for current weather and forecasts.
    // Will be replaced with API calls later.
    private val currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeatherState = currentWeather.asStateFlow()

    private val dailyForecasts = MutableStateFlow<List<DailyForecast>>(emptyList())
    val dailyForecastsState = dailyForecasts.asStateFlow()

    init {
        val current = CurrentWeather(
            conditionId = 1,
            condition = "Sunny",
            temp = 22.5,
            feelTemp = 20,
            precip = "0mm",
            windDir = "NW",
            windSpeed = 12.0
        )
        currentWeather.value = current

        val week: List<DailyForecast> = listOf(
            DailyForecast(
                date = "2025-09-19",
                conditionId = 1,
                condition = "Sunny",
                highTemp = 25.0,
                lowTemp = 15.0,
                precipType = "Clear",
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
        dailyForecasts.value = week

        // Simulating API call delay
        viewModelScope.launch {
            delay(10000)

            val updatedCurrent = CurrentWeather(
                conditionId = 2,
                condition = "Cloudy",
                temp = 23.0,
                feelTemp = 21,
                precip = "1mm",
                windDir = "NE",
                windSpeed = 11.0
            )
            currentWeather.value = updatedCurrent

            val updatedWeek: List<DailyForecast> = listOf(
                DailyForecast(
                    date = "2025-09-22",
                    conditionId = 4,
                    condition = "Stormy",
                    highTemp = 22.0,
                    lowTemp = 13.0,
                    precipType = "Rain",
                    precipAmount = 15.0,
                    precipChance = 90,
                    windDir = "W",
                    windSpeed = 13.0,
                    humidity = 90
                ),
                DailyForecast(
                    date = "2025-09-23",
                    conditionId = 5,
                    condition = "Snowy",
                    highTemp = 19.0,
                    lowTemp = 11.0,
                    precipType = "Snow",
                    precipAmount = 20.0,
                    precipChance = 95,
                    windDir = "SW",
                    windSpeed = 1.0,
                    humidity = 100
                ),
                DailyForecast(
                    date = "2025-09-24",
                    conditionId = 6,
                    condition = "Stormy",
                    highTemp = 1.0,
                    lowTemp = 0.0,
                    precipType = "Rain",
                    precipAmount = 25.0,
                    precipChance = 100,
                    windDir = "N",
                    windSpeed = 0.0,
                    humidity = 0
                )
            )
            dailyForecasts.value = updatedWeek
        }
    }
}
