package com.example.gavmacdonald_weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

@Composable
fun DailyForecastScreen(viewModel: MainViewModel) {
    val txtPadding = Modifier.padding(8.dp)
    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
    val forecast by viewModel.dailyForecasts.collectAsState()

    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        LazyColumn(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            items(forecast) { forecastDay ->
                val iconUrl = forecastDay.day.condition.icon
                val fullIconUrl = if (iconUrl.startsWith("//")) "https:$iconUrl" else iconUrl
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
                        AsyncImage(
                            model = fullIconUrl,
                            contentDescription = forecastDay.day.condition.text,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(8.dp)
                        )
                        Text("Date: ${forecastDay.date}",
                            txtPadding)
                        Text("Conditions: ${forecastDay.day.condition.text}",
                            txtPadding)
                        Text("Temperatures: High: ${forecastDay.day.maxtemp_c} Low: ${forecastDay.day.mintemp_c}",
                            txtPadding)
                        Text("Precipitation: ${forecastDay.day.daily_chance_of_rain} %",
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