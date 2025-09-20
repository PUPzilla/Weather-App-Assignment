package com.example.gavmacdonald_weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gavmacdonald_weatherapp.viewmodel.WeatherViewModel

@Composable
fun CurrentWeatherScreen(viewModel: WeatherViewModel) {
    val current = viewModel.currentWeather
    Column(modifier = Modifier.padding(8.dp)) {
        Image(painterResource(current.weatherImg), contentDescription = null)
        Text("Conditions: ${current.condition}")
        Text("Temperature: ${current.temp}")
        Text("Precipitation: ${current.precip}")
        Text("Wind: ${current.windDir} ${current.windSpeed}km/h")
    }
}