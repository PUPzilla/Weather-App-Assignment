package com.example.gavmacdonald_weatherapp.services

import com.example.gavmacdonald_weatherapp.models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
//    https://api.weatherapi.com/v1/forecast.json?key=74ec7199c6de433d88d182829251510&q=Halifax&aqi=no
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = "74ec7199c6de433d88d182829251510",
        // Hard-coded to Halifax for now. Replace with user location later.
        @Query("q") q: String = "Halifax",
        @Query("days") days: Int = 3
    ): WeatherResponse
}

data class WeatherResponse(
    val location: Location,
    val current: CurrentWeather,
    val forecast: Forecast
)