package com.codewithmehyo.androidtestatgmobile.features.player

import com.codewithmehyo.androidtestatgmobile.manager.ExoPlayerManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single { ExoPlayerManager(context = androidContext()) }
    viewModel {
        PlayerViewModel(
            exoPLayerManger = get(),
            subscriptionManager = get()
        )
    }
}