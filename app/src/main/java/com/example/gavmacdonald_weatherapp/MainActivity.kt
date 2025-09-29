package com.example.gavmacdonald_weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gavmacdonald_weatherapp.ui.screens.CurrentWeatherScreen
import com.example.gavmacdonald_weatherapp.ui.screens.DailyForecastScreen
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
                DisplayUI(mainViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(viewModel: MainViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Halifax, Nova Scotia") },
                colors =
                    TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer)
                )
        },
        bottomBar = {
            NavigationBar(
                containerColor =
                    MaterialTheme.colorScheme.primaryContainer
            ) {
                NavigationBarItem(
                    icon = {
                        Icon(painterResource(R.drawable.rounded_home_24),
                            contentDescription = "Current Weather Icon")},
                    label = { Text("Current") },
                    selected = (navController.currentDestination?.route == "current"),
                    onClick = { navController.navigate("current") }
                )
                NavigationBarItem(
                    icon = {
                        Icon(painterResource(R.drawable.rounded_calendar_today_24),
                            contentDescription = "Daily Forecast Icon")
                    },
                    label = { Text("Forecast") },
                    selected = (navController.currentDestination?.route == "forecast"),
                    onClick = { navController.navigate("forecast") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "current",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("current") { CurrentWeatherScreen(viewModel) }
            composable("forecast") { DailyForecastScreen(viewModel) }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun PreviewUI(){
    DisplayUI(MainViewModel())
}