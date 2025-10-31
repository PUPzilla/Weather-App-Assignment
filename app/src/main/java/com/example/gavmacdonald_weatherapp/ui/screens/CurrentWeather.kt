package com.example.gavmacdonald_weatherapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CurrentWeatherScreen(viewModel: MainViewModel) {
    val txtPadding = Modifier.padding(24.dp)
    val textStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    val current by viewModel.currentWeather.collectAsState()
    val hourly by viewModel.hourlyForecast.collectAsState()

    val fullIconUrl = current?.condition?.icon?.let { icon ->
        if (icon.startsWith("//")) "https:$icon" else icon
    } ?: ""

    if (current != null) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                //  Condition icon
                AsyncImage(
                    model = fullIconUrl,
                    contentDescription = "Weather Icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(128.dp)
                )

                //  Weather info
                Text(
                    text = current!!.condition.text,
                    modifier = txtPadding,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    text = "${current!!.temp_c} ℃",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold)
                Text(
                    text = "(Feels Like: ${current!!.feelslike_c} ℃)",
                    modifier = txtPadding,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                if (current?.precip_mm != 0.0) {
                    Text(
                        text = "Precipitation: ${current!!.precip_mm} mm",
                        modifier = txtPadding
                    )
                }
                Text(
                    text = "Wind: ${current!!.wind_dir} ${current?.wind_kph} km/h",
                    modifier = txtPadding,
                    style = textStyle
                )

                Spacer(modifier = Modifier.size(36.dp))
                if (hourly.isNotEmpty()) {
                    Text(
                        text = "Hourly Weather",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = txtPadding
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(hourly.take(12)) { hour ->
                            HourlyWeatherCard(hour.time, hour.temp_c, hour.condition.icon)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HourlyWeatherCard(time: String, temp: Double, iconUrl: String) {
    val displayedTime = try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("ha", Locale.getDefault())
        val date = inputFormat.parse(time)
        outputFormat.format(date ?: time)
    } catch (_: Exception) {
        time
    }

    val fullIconUrl = if (iconUrl.startsWith("//")) "https:$iconUrl" else iconUrl

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .size(width = 80.dp, height = 140.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = fullIconUrl,
                contentDescription = "Hourly Weather Icon",
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit
            )
            Text(displayedTime, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.size(4.dp))
            Text("$temp ℃", fontSize = 16.sp)
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = false)
@Composable
fun PreviewUI(){
    CurrentWeatherScreen(MainViewModel())
}