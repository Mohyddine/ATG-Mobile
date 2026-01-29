package com.codewithmehyo.androidtestatgmobile.features.main

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val route: AppDestination
)
