package com.codewithmehyo.androidtestatgmobile.features.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
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
class PlayerViewModel() : ViewModel() {
    // A state flow to keep track of whether the player is currently playing.
    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    private var updateJob: Job? = null

    /**
     * Toggles the playback of the video.
     *
     * @param player The ExoPlayer instance.
     */
    fun togglePlay(player: ExoPlayer) {
        if (player.currentPosition >= player.duration && player.duration > 0) {
            player.seekTo(0L)
        }
        if (player.isPlaying) {
            player.pause()
        } else {
            player.play()
        }
        startUpdatingPosition(player)
        _uiState.update { it.copy(isPlaying = player.isPlaying) }
    }

    private fun startUpdatingPosition(player: ExoPlayer) {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            while (true) {
                _uiState.update {
                    it.copy(
                        currentPosition = player.currentPosition,
                        duration = player.duration
                    )
                }
                delay(500L) // update every 0.5s
            }
        }
    }

    fun seekBy(player: ExoPlayer, millis: Long) {
        val newPosition = (player.currentPosition + millis).coerceIn(0, player.duration)
        player.seekTo(newPosition)
        _uiState.update { it.copy(currentPosition = newPosition) }
    }

    fun seekTo(player: ExoPlayer, position: Long) {
        val pos = position.coerceIn(0, player.duration)
        player.seekTo(pos)
        _uiState.update { it.copy(currentPosition = pos) }
    }
}