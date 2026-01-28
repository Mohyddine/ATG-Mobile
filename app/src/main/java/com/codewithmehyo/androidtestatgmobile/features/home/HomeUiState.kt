package com.codewithmehyo.androidtestatgmobile.features.home

data class HomeUiState(
    val isSubscribed: Boolean = false,
    val horizontalItems: List<MediaItemUI> = emptyList(),
    val verticalItems: List<MediaItemUI> = emptyList(),
    val topItems: List<MediaItemUI> = emptyList(),
)