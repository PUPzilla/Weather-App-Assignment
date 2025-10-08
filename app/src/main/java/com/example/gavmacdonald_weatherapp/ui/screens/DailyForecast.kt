package com.example.gavmacdonald_weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gavmacdonald_weatherapp.models.loadIcon
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

@Composable
fun DailyForecastScreen(viewModel: MainViewModel) {
    val txtPadding = Modifier.padding(8.dp)
    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
    val forecast = viewModel.dailyForecastsState

    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(forecast.value) { forecast ->
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Column(
                        Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(
                                loadIcon(forecast.conditionId)),
                            contentDescription = null,
                            Modifier.size(100.dp)
                        )
                    }
                    Column(
                        Modifier.padding(16.dp)
                    ) {
                        Text("Date: ${forecast.date}",
                            txtPadding)
                        Text("Conditions: ${forecast.condition}",
                            txtPadding)
                        Text("Temperatures: High: ${forecast.highTemp} Low: ${forecast.lowTemp}",
                            txtPadding)
                        Text("Precipitation: ${forecast.precipType} ${forecast.precipAmount}mm (${forecast.precipChance}%)",
                            txtPadding)
                        Text("Wind: ${forecast.windDir} ${forecast.windSpeed}km/h",
                            txtPadding)
                        Text("Humidity: ${forecast.humidity}%",
                            txtPadding)
                    }
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewDaily() {
    DailyForecastScreen(MainViewModel())
}