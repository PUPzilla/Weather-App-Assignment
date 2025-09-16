package com.example.gavmacdonald_weatherapp.models

class Weather {
    var weatherImage = null
    var condition = null
    var temperature = null
    var precipitation = null
    var windDir = null
    var windSpeed = null
}

class Forecast {
    val date = null
    var weatherImg = null
    var tempHigh = null
    var tempLow = null
    var conditions = null
    var precipitation = null
    var windDir = null
    var windSpeed = null
    var humidity = null
}

fun ThreeDayForecast() {
    var forecast = arrayOf(Forecast())
}