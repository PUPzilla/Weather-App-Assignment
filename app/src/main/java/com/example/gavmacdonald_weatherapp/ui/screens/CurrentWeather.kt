package com.example.gavmacdonald_weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

@Composable
fun CurrentWeatherScreen(viewModel: MainViewModel) {
    val txtPadding = Modifier.padding(24.dp)
    val textStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    val current by viewModel.currentWeather.collectAsState()

    val fullIconUrl = current?.condition?.icon?.let { icon ->
        if (icon.startsWith("//")) "https:$icon" else icon
    } ?: ""

    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            current?.let {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    //  Condition icon
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        WeatherIcon(
                            iconUrl = fullIconUrl,
                            modifier = Modifier.size(64.dp)
                        )
                    }

                    //  Weather info
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Conditions: ${it.condition.text}", modifier = txtPadding)
                        Text("${it.temp_c} ℃", modifier = txtPadding, fontSize = 64.sp)
                        Text("Feels Like: ${it.feelslike_c} ℃", modifier = txtPadding)
                        if (it.precip_mm != 0.0) {
                            Text("Precipitation: ${it.precip_mm} mm", modifier = txtPadding)
                        }
                        Text("Wind: ${it.wind_dir} ${it.wind_kph} km/h", modifier = txtPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherIcon(iconUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = iconUrl,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewUI(){
    CurrentWeatherScreen(MainViewModel())
}