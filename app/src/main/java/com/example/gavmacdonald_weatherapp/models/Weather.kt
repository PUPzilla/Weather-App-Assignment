package com.example.gavmacdonald_weatherapp.models

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.gavmacdonald_weatherapp.R

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

fun loadIcons(context: Context): Array<Drawable?> {
    // Array of drawable resource IDs
    val iconIds = arrayOf(
        R.drawable.rounded_home_24,
        R.drawable.rounded_calendar_today_24,
        R.drawable.rounded_edit_location_alt_24,
        R.drawable.rounded_sunny_24,
        R.drawable.rounded_cloud_24,
        R.drawable.rounded_rainy_24,
        R.drawable.rounded_mist_24,
        R.drawable.rounded_rainy_heavy_24,
        R.drawable.rounded_foggy_24,
        R.drawable.rounded_thunderstorm_24,
        R.drawable.rounded_storm_24,
        R.drawable.rounded_weather_snowy_24,

    )

    return iconIds.map { id: Int -> ContextCompat.getDrawable(context, id) }.toTypedArray()
}