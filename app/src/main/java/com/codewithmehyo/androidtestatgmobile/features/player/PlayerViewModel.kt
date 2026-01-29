package com.codewithmehyo.androidtestatgmobile.features.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.codewithmehyo.androidtestatgmobile.manager.ExoPlayerManager
import com.codewithmehyo.androidtestatgmobile.manager.SubscriptionManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the player screen.
 */
class PlayerViewModel(
    private val exoPLayerManger: ExoPlayerManager,
    private val subscriptionManager: SubscriptionManager
) : ViewModel() {

    val exoPlayer: ExoPlayer = exoPLayerManger.getOrCreatePlayer(
        subscriptionManager.isSubscribed,
        "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8",
        "https://pubads.g.doubleclick.net/gampad/ads?iu=/21775744923/external/vmap_ad_samples&sz=640x480&cust_params=sample_ar%3Dpremidpost&ciu_szs=300x250&gdfp_req=1&ad_rule=1&output=vmap&unviewed_position_start=1&env=vp&cmsid=496&vid=short_onecue&correlator="
    )

    // A state flow to keep track of whether the player is currently playing.
    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private var updateJob: Job? = null

    fun prepareIfNeeded() {
        startUpdatingPosition()
    }

    /**
     * Toggles the playback of the video.
     *
     * @param player The ExoPlayer instance.
     */
    fun togglePlay() {
        if (exoPlayer.currentPosition >= exoPlayer.duration && exoPlayer.duration > 0) {
            exoPlayer.seekTo(0L)
        }
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
        _uiState.update { it.copy(isPlaying = exoPlayer.isPlaying) }
    }

    private fun startUpdatingPosition() {
        if (updateJob != null) return

        updateJob = viewModelScope.launch {
            while (true) {
                _uiState.update {
                    it.copy(
                        currentPosition = exoPlayer.currentPosition,
                        duration = exoPlayer.duration,
                        isPlaying = exoPlayer.isPlaying
                    )
                }
                delay(500)
            }
        }
    }

    fun seekBy(millis: Long) {
        val newPosition = (exoPlayer.currentPosition + millis).coerceIn(0, exoPlayer.duration)
        exoPlayer.seekTo(newPosition)
        _uiState.update { it.copy(currentPosition = newPosition) }
    }

    fun seekTo(position: Long) {
        val pos = position.coerceIn(0, exoPlayer.duration)
        exoPlayer.seekTo(pos)
        _uiState.update { it.copy(currentPosition = pos) }
    }

    override fun onCleared() {
        exoPLayerManger.release()
        super.onCleared()
    }
}