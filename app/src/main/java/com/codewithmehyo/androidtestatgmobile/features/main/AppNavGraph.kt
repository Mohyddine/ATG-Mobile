package com.codewithmehyo.androidtestatgmobile.features.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.codewithmehyo.androidtestatgmobile.features.home.HomeScreen
import com.codewithmehyo.androidtestatgmobile.features.home.HomeViewModel
import com.codewithmehyo.androidtestatgmobile.features.player.PlayerScreen
import com.codewithmehyo.androidtestatgmobile.features.profile.ProfileScreen
import org.koin.androidx.compose.koinViewModel

/**
 * The main navigation graph for the application.
 */
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    startDestination: AppDestination,
    navController: NavHostController,
    onExitAppClick: () -> Unit = {}
) {
    // Get the HomeViewModel instance using koin.
    val viewModel = koinViewModel<HomeViewModel>()
    // Collect the UI state from the HomeViewModel as a Compose state.
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // NavHost is a container that displays the current destination.
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // Define the composable for the Home destination.
        composable<AppDestination.Home> {
            HomeScreen(
                onItemClick = {

                    navController.navigate(AppDestination.Player)
                },
                onSubscriptionClick = {
                    // Toggle the subscription status when the subscription card is clicked.
                    viewModel.toggleSubscription()
                },
                isSubscribed = state.isSubscribed,
                topItems = state.topItems,
                verticalItems = state.verticalItems,
                horizontalItems = state.horizontalItems,
            )
        }

        // Define the composable for the Player destination.
        composable<AppDestination.Player> { backStackEntry ->
            // Extract the arguments from the navigation entry.
            val destination = backStackEntry.toRoute<AppDestination.Player>()

            // Display the PlayerScreen with the provided arguments.
            PlayerScreen(
                isSubscribed = state.isSubscribed,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        composable<AppDestination.Profile> {
            ProfileScreen(
                isSubscribed = state.isSubscribed,
                onExitAppClick = onExitAppClick
            )
        }
    }
}