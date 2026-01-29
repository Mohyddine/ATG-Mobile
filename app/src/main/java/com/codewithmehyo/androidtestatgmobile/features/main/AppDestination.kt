package com.codewithmehyo.androidtestatgmobile.features.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination {

    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Player : AppDestination

    @Serializable
    data object Profile : AppDestination
}