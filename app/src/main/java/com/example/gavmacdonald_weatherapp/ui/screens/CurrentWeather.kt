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
fun CurrentWeatherScreen(viewModel: MainViewModel) {
    val txtPadding = Modifier.padding(24.dp)
    val textStyle = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
    val current = viewModel.currentWeatherState.value ?: return

    CompositionLocalProvider(LocalTextStyle provides textStyle) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        painterResource(id = loadIcon(current.conditionId)),
                        contentDescription = null,
                        Modifier
                            .size(150.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text("Conditions: ${current.condition}",
                        modifier = txtPadding)
                    Text("Temperature: ${current.temp}",
                        modifier = txtPadding)
                    Text("Precipitation: ${current.precip}",
                        modifier = txtPadding)
                    Text("Wind: ${current.windDir} ${current.windSpeed}km/h",
                        modifier = txtPadding)
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewUI(){
    CurrentWeatherScreen(MainViewModel())
}