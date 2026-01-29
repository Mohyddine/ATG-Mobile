package com.codewithmehyo.androidtestatgmobile.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codewithmehyo.androidtestatgmobile.ui.theme.AndroidTestATGMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTestATGMobileTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
                val mainDestinations = listOf(
                    MenuItem(
                        title = "Home",
                        icon = Icons.Filled.Home,
                        route = AppDestination.Home,
                    ),
                    MenuItem(
                        title = "Profile",
                        icon = Icons.Filled.Person,
                        route = AppDestination.Profile,
                    )
                )
                val showBottomBar =
                    currentRoute?.contains(mainDestinations[selectedDestination].title) ?: false

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                                mainDestinations.forEachIndexed { index, destination ->
                                    NavigationBarItem(
                                        selected = selectedDestination == index,
                                        onClick = {
                                            navController.navigate(route = destination.route)
                                            selectedDestination = index
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = destination.icon,
                                                contentDescription = destination.title
                                            )
                                        },
                                        label = { Text(destination.title) }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    AppNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = mainDestinations.first().route,
                        navController = navController,
                        onExitAppClick = { finish() }
                    )
                }
            }
        }
    }
}
