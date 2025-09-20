package com.example.gavmacdonald_weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gavmacdonald_weatherapp.viewmodel.WeatherViewModel

@Composable
fun DailyForecastScreen(viewModel: WeatherViewModel) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(viewModel.dailyForecasts) { forecast ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
                Column(Modifier.padding(8.dp)) {
                    Image(painter = painterResource(forecast.weatherImg), contentDescription = null)
                    Text("Date: ${forecast.date}")
                    Text("Conditions: ${forecast.condition}")
                    Text("Temperatures: High: ${forecast.highTemp} Low: ${forecast.lowTemp}")
                    Text("Precipitation: ${forecast.precipType} ${forecast.precipAmount}mm (${forecast.precipChance}%)")
                    Text("Wind: ${forecast.windDir} ${forecast.windSpeed}km/h")
                    Text("Humidity: ${forecast.humidity}%")
                }
            }
        }
    }
}