package com.example.gavmacdonald_weatherapp.models

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime: String
)

data class Condition(
    val text: String,
    val icon: String
)

data class CurrentWeather(
    val last_updated: String,
    val temp_c: Double,
    val is_day: Int,
    val condition: Condition,
    val wind_kph: Double,
    val wind_dir: String,
    val precip_mm: Double,
    val humidity: Int,
    val feelslike_c: Double
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int
)

data class Hour(
    val time: String,
    val temp_c: Double,
    val condition: Condition,
    val wind_kph: Double,
    val wind_dir: String,
    val precip_mm: Double
)
