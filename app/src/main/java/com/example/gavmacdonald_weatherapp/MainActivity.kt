package com.example.gavmacdonald_weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gavmacdonald_weatherapp.ui.screens.CurrentWeatherScreen
import com.example.gavmacdonald_weatherapp.ui.screens.DailyForecastScreen
import com.example.gavmacdonald_weatherapp.ui.theme.GavMacDonaldWeatherAppTheme
import com.example.gavmacdonald_weatherapp.ui.theme.ThemeMode
import com.example.gavmacdonald_weatherapp.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            var currentThemeMode by rememberSaveable { mutableStateOf(ThemeMode.System) }
            GavMacDonaldWeatherAppTheme(themeMode = currentThemeMode) {
                DisplayUI(
                    mainViewModel,
                    currentThemeMode = currentThemeMode,
                    onThemeModeChange = { newMode -> currentThemeMode = newMode }
                )
            }
            GetLocation()
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
    // Placeholder location.
    // Will replace this later with a call to fetch the user's real location.
    val locationName = "Halifax"
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        viewModel.refreshWeather(locationName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(locationName) },
                actions = {
                    //  Refresh button
                    IconButton(onClick = { viewModel.refreshWeather(locationName) }) {
                        Icon(
                            painterResource(R.drawable.round_refresh_24),
                            contentDescription = "Refresh Data"
                        )
                    }
                    //  Theme selection dropdown
                    ThemeSelection(
                        currentThemeMode = currentThemeMode,
                        onThemeModeChange = onThemeModeChange
                    )
                }
            )
        },
        //  Screen navigation
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            NavigationBar {
                //  Current weather screen
                NavigationBarItem(
                    icon = {
                        Icon(painterResource(R.drawable.rounded_home_24),
                            contentDescription = "Current Weather Icon"
                        )
                    },
                    label = { Text("Current") },
                    selected = currentRoute == "current",
                    onClick = {
                        if (currentRoute != "current") {
                            navController.navigate("current") {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                //  Daily forecast screen
                NavigationBarItem(
                    icon = {
                        Icon(painterResource(R.drawable.rounded_calendar_today_24),
                            contentDescription = "Daily Forecast Icon"
                        )
                    },
                    label = { Text("Forecast") },
                    selected = currentRoute == "forecast",
                    onClick = {
                        navController.navigate("forecast") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocation() {
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if(permissionState.status.isGranted) {
        Log.i("TESTING", "Location permission granted...\nNuclear strike inbound.")

        val currentContext = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

        if(ContextCompat.checkSelfPermission(
                currentContext,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val cancellationTokenSource = CancellationTokenSource()

            Log.i("TESTING", "Requesting location...")

            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val long = location.longitude.toString()

                        Log.i("TESTING", "Location: ${location.latitude}, ${location.longitude}")

                        val coordinates = "$lat $long"
                    }
                }
        }
    }
    else {
        LaunchedEffect(permissionState) {
            permissionState.launchPermissionRequest()
        }
    }
}

//  Theme selection dropdown menu
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
            //  List themes to choose from
            DropdownMenuItem(
                text = { Text(mode.name) },
                onClick = {
                    onThemeModeChange(mode)
                    expanded = false
                },
            //  Checkmark next to selected theme
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