package com.example.gavmacdonald_weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

@Composable
fun DailyForecastScreen(viewModel: MainViewModel) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(viewModel.dailyForecasts) { forecast ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(forecast.weatherImg), contentDescription = null)
                    }
                Column(
                    Modifier
                        .padding(16.dp)
                ) {
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

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun PreviewDaily() {
    DailyForecastScreen(MainViewModel())
}