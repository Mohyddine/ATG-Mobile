package com.codewithmehyo.androidtestatgmobile.features.home

import androidx.lifecycle.ViewModel
import com.codewithmehyo.androidtestatgmobile.R
import com.codewithmehyo.androidtestatgmobile.features.home.model.HomeUiState
import com.codewithmehyo.androidtestatgmobile.features.home.model.MediaItemUI
import com.codewithmehyo.androidtestatgmobile.manager.SubscriptionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the home screen.
 *
 * @property subscriptionManager The subscription manager.
 */
class HomeViewModel(
    private val subscriptionManager: SubscriptionManager
) : ViewModel() {

    // The UI state for the home screen, containing subscription status and media item lists.
    private val _uiState = MutableStateFlow(
        HomeUiState(
            isSubscribed = subscriptionManager.isSubscribed,
            verticalItems = getVerticalItems(),
            horizontalItems = getHorizontalItems(),
            topItems = getTopItems()
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /**
     * Toggles the user's subscription status and updates the UI state.
     */
    fun toggleSubscription() {
        val newValue = !subscriptionManager.isSubscribed
        subscriptionManager.isSubscribed = newValue

        _uiState.update {
            it.copy(isSubscribed = newValue)
        }
    }

    /**
     * Returns a list of media items for the vertical carousel.
     */
    private fun getVerticalItems(): List<MediaItemUI> {
        return List(10) {
            MediaItemUI(
                id = it,
                title = "Media $it",
                imagePlaceHolder = R.drawable.img_vertical,
                imageUrl = "https://picsum.photos/300/500?random=${it + 100}",
            )
        }
    }

    /**
     * Returns a list of media items for the horizontal carousel.
     */
    private fun getHorizontalItems(): List<MediaItemUI> {
        return List(10) {
            MediaItemUI(
                id = it,
                title = "Media $it",
                imagePlaceHolder = R.drawable.img_horizontal,
                imageUrl = "https://picsum.photos/500/300?random=$${it + 200}",
            )
        }
    }

    /**
     * Returns a list of media items for the top carousel.
     */
    private fun getTopItems(): List<MediaItemUI> {
        return List(10) {
            MediaItemUI(
                id = it,
                title = "Media $it",
                imagePlaceHolder = R.drawable.img_horizontal,
                imageUrl = "https://picsum.photos/720/500?random=${it + 300}",
            )
        }
    }
}