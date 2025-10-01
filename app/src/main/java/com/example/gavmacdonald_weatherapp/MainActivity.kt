package com.example.gavmacdonald_weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gavmacdonald_weatherapp.ui.screens.CurrentWeatherScreen
import com.example.gavmacdonald_weatherapp.ui.screens.DailyForecastScreen
import com.example.gavmacdonald_weatherapp.ui.theme.GavMacDonaldWeatherAppTheme
import com.example.gavmacdonald_weatherapp.ui.theme.ThemeMode
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            var currentThemeMode by remember { mutableStateOf(ThemeMode.System) }

            GavMacDonaldWeatherAppTheme(themeMode = currentThemeMode) {
                DisplayUI(
                    mainViewModel,
                    currentThemeMode = currentThemeMode,
                    onThemeModeChange = { currentThemeMode = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(
    viewModel: MainViewModel,
    currentThemeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    val locationName = "Halifax, Nova Scotia"
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(locationName) },
                actions = {
                    ThemeSelection(
                        currentThemeMode = currentThemeMode,
                        onThemeModeChange = onThemeModeChange
                    )
                }
            )
        },
        bottomBar = {
            NavigationBar {
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

@Composable
fun ThemeSelection(
    currentThemeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Select Theme",
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        ThemeMode.entries.forEach { mode ->
            DropdownMenuItem(
                text = { Text(mode.name) },
                onClick = {
                    onThemeModeChange(mode)
                    expanded = false
                },
                leadingIcon = {
                    if (mode == currentThemeMode) {
                        Icon(Icons.Default.Check, contentDescription = "Selected")
                    }
                }
            )
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewUI(){
    GavMacDonaldWeatherAppTheme(themeMode = ThemeMode.Dark) {
        DisplayUI(
            viewModel = MainViewModel(),
            currentThemeMode = ThemeMode.Light,
            onThemeModeChange = { }
        )
    }
}