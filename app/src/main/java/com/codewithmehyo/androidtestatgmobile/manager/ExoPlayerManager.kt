package com.codewithmehyo.androidtestatgmobile.manager

import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.ima.ImaAdsLoader
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory

class ExoPlayerManager(
    context: Context
) {
    private val appContext = context.applicationContext

    private var player: ExoPlayer? = null
    private var adsLoader: ImaAdsLoader? = null

    fun getOrCreatePlayer(
        isSubscribed: Boolean,
        mediaUrl: String,
        adTagUrl: String
    ): ExoPlayer {
        if (player != null) return player!!

        adsLoader = if (!isSubscribed) {
            ImaAdsLoader.Builder(appContext).build()
        } else null

        val mediaSourceFactory =
            DefaultMediaSourceFactory(appContext).apply {
                setLocalAdInsertionComponents({ adsLoader }, { null })
            }

        player = ExoPlayer.Builder(appContext)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()

        adsLoader?.setPlayer(player)

        val mediaItem = MediaItem.Builder()
            .setUri(mediaUrl.toUri())
            .apply {
                if (!isSubscribed) {
                    setAdsConfiguration(
                        MediaItem.AdsConfiguration.Builder(adTagUrl.toUri()).build()
                    )
                }
            }
            .build()

        player!!.setMediaItem(mediaItem)
        player!!.prepare()
        player!!.playWhenReady = true

        return player!!
    }

    fun release() {
        adsLoader?.setPlayer(null)
        adsLoader?.release()
        adsLoader = null
        player?.release()
        player = null
    }
}