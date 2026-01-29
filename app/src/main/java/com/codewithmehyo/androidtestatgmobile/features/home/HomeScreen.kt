package com.codewithmehyo.androidtestatgmobile.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.codewithmehyo.androidtestatgmobile.R
import com.codewithmehyo.androidtestatgmobile.features.home.model.MediaItemUI
import com.codewithmehyo.androidtestatgmobile.ui.components.HorizontalCarousel
import com.codewithmehyo.androidtestatgmobile.ui.components.SubscriptionCard
import com.codewithmehyo.androidtestatgmobile.ui.components.VerticalCarousel

/**
 * The main screen of the application.
 *
 * @param modifier The modifier to be applied to the screen.
 * @param isSubscribed Whether the user is subscribed or not.
 * @param onSubscriptionClick The callback to be invoked when the subscription card is clicked.
 * @param onItemClick The callback to be invoked when a media item is clicked.
 * @param topItems The list of media items to be displayed in the top carousel.
 * @param verticalItems The list of media items to be displayed in the vertical carousel.
 * @param horizontalItems The list of media items to be displayed in the horizontal carousel.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    isSubscribed: Boolean,
    onSubscriptionClick: () -> Unit = {},
    onItemClick: () -> Unit = {},
    topItems: List<MediaItemUI> = listOf(),
    verticalItems: List<MediaItemUI> = listOf(),
    horizontalItems: List<MediaItemUI> = listOf()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true
            )
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.home_label),
            style = MaterialTheme.typography.displayLarge,
            color = Color.White
        )
        Spacer(Modifier.height(24.dp))
        SubscriptionCard(
            modifier = Modifier.padding(start = 16.dp),
            onSubscriptionClick = onSubscriptionClick,
            isSubscribed = isSubscribed
        )
        Spacer(Modifier.height(32.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.vertical_images_label),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        VerticalCarousel(
            onMediaClick = onItemClick,
            items = verticalItems
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.horizontal_images_label),
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
        Spacer(Modifier.height(32.dp))
        HorizontalCarousel(
            onMediaClick = onItemClick,
            items = horizontalItems
        )
        Spacer(Modifier.height(32.dp))
    }
}
