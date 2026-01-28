package com.codewithmehyo.androidtestatgmobile.features.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination {

    @Serializable
    data object Home : AppDestination

    @Serializable
    data class Player(val mediaUrl: String, val adTagUrl: String) : AppDestination
}