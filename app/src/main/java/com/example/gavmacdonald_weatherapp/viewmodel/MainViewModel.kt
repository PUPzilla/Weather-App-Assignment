package com.example.gavmacdonald_weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gavmacdonald_weatherapp.models.CurrentWeather
import com.example.gavmacdonald_weatherapp.models.ForecastDay
import com.example.gavmacdonald_weatherapp.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeather = _currentWeather.asStateFlow()
    private val _dailyForecasts = MutableStateFlow<List<ForecastDay>>(emptyList())
    val dailyForecasts = _dailyForecasts.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService: WeatherService = retrofit.create(WeatherService::class.java)

    fun refreshWeather(location: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val forecastResponse = weatherService.getForecast(
                    key = "74ec7199c6de433d88d182829251510",
                    q = location,
                    days = 3
                )

                _currentWeather.value = forecastResponse.current
                _dailyForecasts.value = forecastResponse.forecast.forecastday
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Failed to fetch weather data"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
//    private val _isRefreshing = MutableStateFlow(false)

//    init {
//        loadWeather()
//    }
//    private fun loadWeather() {
//        val current = CurrentWeather(
//            conditionId = 1,
//            condition = "Sunny",
//            temp = 22.5,
//            feelTemp = 20,
//            precip = "0mm",
//            windDir = "NW",
//            windSpeed = 12.0
//        )
//        _currentWeather.value = current
//
//        val week: List<DailyForecast> = listOf(
//            DailyForecast(1, "2025-09-19", "Sunny", 25.0, 15.0, "Clear", 0.0, 0, "NW", 10.0, 50),
//            DailyForecast(2, "2025-09-20", "Cloudy", 20.0, 14.0, "Rain", 5.0, 60, "E", 8.0, 70),
//            DailyForecast(3, "2025-09-21", "Rain", 18.0, 12.0, "Rain", 10.0, 80, "S", 15.0, 85)
//        )
//        _dailyForecasts.value = week
//    }
//
//    fun refreshWeather() {
//        // Simulate a delay before updating the data
//        viewModelScope.launch {
//            _isRefreshing.value = true
//            delay(2000)
//            val updatedCurrent = CurrentWeather(
//                conditionId = 2,
//                condition = "Cloudy",
//                temp = 23.0,
//                feelTemp = 21,
//                precip = "1mm",
//                windDir = "NE",
//                windSpeed = 11.0
//            )
//            _currentWeather.value = updatedCurrent
//            val updatedWeek: List<DailyForecast> = listOf(
//                DailyForecast(4, "2025-09-22", "Heavy Rain", 22.0, 13.0, "Rain", 15.0, 90, "W", 13.0, 90),
//                DailyForecast(5, "2025-09-23", "Snowy", 19.0, 11.0, "Snow", 20.0, 95, "SW", 1.0, 100),
//                DailyForecast(6, "2025-09-24", "Stormy", 1.0, 0.0, "Rain", 25.0, 100, "N", 0.0, 0)
//            )
//            _dailyForecasts.value = updatedWeek
//            _isRefreshing.value = false
//        }
//    }
