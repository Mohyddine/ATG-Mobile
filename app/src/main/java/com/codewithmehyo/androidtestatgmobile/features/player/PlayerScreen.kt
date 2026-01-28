package com.codewithmehyo.androidtestatgmobile.features.player

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.ima.ImaAdsLoader
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import com.codewithmehyo.androidtestatgmobile.ui.components.PlayerControls
import com.codewithmehyo.androidtestatgmobile.ui.theme.AndroidTestATGMobileTheme

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
    mediaUrl: String = "",
    adTagUrl: String = "",
    isSubscribed: Boolean = false,
) {
    val context = LocalContext.current
    // Create an ImaAdsLoader if the user is not subscribed.
    val adsLoader = remember { if (!isSubscribed) ImaAdsLoader.Builder(context).build() else null }
    // Create an ExoPlayer instance.
    val exoPlayer = remember {
        val playerBuilder = ExoPlayer.Builder(context)
        // If the user is not subscribed, set up the ads loader.
        if (!isSubscribed && adsLoader != null) {
            playerBuilder.setMediaSourceFactory(
                DefaultMediaSourceFactory(context)
                    .setLocalAdInsertionComponents(
                        { adsLoader },
                        { null }
                    )
            )
        }
        playerBuilder.build().apply {
            // Only prepare the player if the mediaUrl is not empty.
            if (mediaUrl.isNotEmpty()) {
                // Create a media item builder.
                val mediaItemBuilder = MediaItem.Builder()
                    .setUri(mediaUrl.toUri())

                // If the user is not subscribed, set the ads configuration.
                if (!isSubscribed) {
                    mediaItemBuilder.setAdsConfiguration(
                        MediaItem.AdsConfiguration.Builder(adTagUrl.toUri()).build()
                    )
                }
                // Set the media item to be played.
                setMediaItem(mediaItemBuilder.build())
                // Prepare the player.
                prepare()
                // Start playing when ready.
                playWhenReady = true
            }
        }
    }

    // A DisposableEffect to release the player and ads loader when the composable is disposed.
    DisposableEffect(exoPlayer) {
        adsLoader?.setPlayer(exoPlayer)
        onDispose {
            adsLoader?.setPlayer(null)
            adsLoader?.release()
            exoPlayer.release()
        }
    }

    // A box to hold the player view and controls.
    Box(modifier = modifier.fillMaxSize()) {
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
        PlayerControls(
            exoPlayer = exoPlayer,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerScreenPreview() {
    AndroidTestATGMobileTheme {
        PlayerScreen()
    }
}