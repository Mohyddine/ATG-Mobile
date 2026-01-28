package com.codewithmehyo.androidtestatgmobile.features.player

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    viewModel { PlayerViewModel() }
}