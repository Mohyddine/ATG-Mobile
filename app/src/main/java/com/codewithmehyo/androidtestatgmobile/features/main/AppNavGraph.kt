package com.codewithmehyo.androidtestatgmobile.features.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codewithmehyo.androidtestatgmobile.features.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * The main navigation graph for the application.
 */
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    // Get the HomeViewModel instance using koin.
    val viewModel = koinViewModel<HomeViewModel>()
    // Collect the UI state from the HomeViewModel as a Compose state.
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // NavHost is a container that displays the current destination.
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppDestination.Home
    ) {
        // Define the composable for the Home destination.
        composable<AppDestination.Home> {
            //HomeScreen(
            //    onItemClick = { mediaUrl, adTagUrl ->
            //        // Navigate to the Player screen when a media item is clicked,
            //        // passing the media and ad URLs.
            //        navController.navigate(AppDestination.Player(mediaUrl, adTagUrl))
            //    },
            //    onSubscriptionClick = {
            //        // Toggle the subscription status when the subscription card is clicked.
            //        viewModel.toggleSubscription()
            //    },
            //    isSubscribed = state.isSubscribed,
            //    topItems = state.topItems,
            //    verticalItems = state.verticalItems,
            //    horizontalItems = state.horizontalItems,
            //)
        }

        // Define the composable for the Player destination.
        composable<AppDestination.Player> { backStackEntry ->
            // Extract the arguments from the navigation entry.
            val destination = backStackEntry.toRoute<AppDestination.Player>()

            // Display the PlayerScreen with the provided arguments.
            //PlayerScreen(
            //    mediaUrl = destination.mediaUrl,
            //    adTagUrl = destination.adTagUrl,
            //    isSubscribed = state.isSubscribed,
            //)
        }
    }
}