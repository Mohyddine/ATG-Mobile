package com.codewithmehyo.androidtestatgmobile.features.player

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.SettingsRemote
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.codewithmehyo.androidtestatgmobile.ui.components.PlayerControls
import com.codewithmehyo.androidtestatgmobile.ui.theme.AndroidTestATGMobileTheme
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

/**
 * A screen that plays a video with ads, unless the user is subscribed.
 *
 * @param modifier The modifier to be applied to the screen.
 * @param mediaUrl The URL of the media to be played.
 * @param adTagUrl The URL of the ad tag to be used.
 * @param isSubscribed Whether the user is subscribed or not.
 */
@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    modifier: Modifier = Modifier,
    isSubscribed: Boolean = false,
    onNavigateBack: () -> Unit = {},
    viewModel: PlayerViewModel = koinViewModel()
) {
    var isControlVisible by remember { mutableStateOf(true) }
    val exoPlayer = viewModel.exoPlayer
    LaunchedEffect(Unit) {
        viewModel.prepareIfNeeded()
    }
    DisposableEffect(Unit) {
        onDispose {
            // Detach surface on recomposition
            exoPlayer.clearVideoSurface()
        }
    }
    // Auto hide controls
    LaunchedEffect(isControlVisible) {
        if (isControlVisible) {
            delay(15000L)
            isControlVisible = false
        }
    }
    // A box to hold the player view and controls.
    Box(modifier = modifier.fillMaxSize()) {
        IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "ArrowBack"
            )
        }
        // An AndroidView to display the player.
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                }
            }
        )
        // The player controls.
        if (isControlVisible) {
            PlayerControls(modifier = Modifier.fillMaxSize())
        } else {
            IconButton(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = { isControlVisible = true }
            ) {
                Icon(
                    imageVector = Icons.Default.SettingsRemote,
                    contentDescription = "ArrowBack"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerScreenPreview() {
    AndroidTestATGMobileTheme {
        PlayerScreen()
    }
}