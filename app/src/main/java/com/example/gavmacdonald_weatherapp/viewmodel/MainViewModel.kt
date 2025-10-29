package com.example.gavmacdonald_weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gavmacdonald_weatherapp.models.CurrentWeather
import com.example.gavmacdonald_weatherapp.models.ForecastDay
import com.example.gavmacdonald_weatherapp.models.Location
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
    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()
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
                _location.value = forecastResponse.location
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Failed to fetch weather data"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
