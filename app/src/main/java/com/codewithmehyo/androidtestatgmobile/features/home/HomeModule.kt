package com.codewithmehyo.androidtestatgmobile.features.home

import com.codewithmehyo.androidtestatgmobile.manager.SubscriptionManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single { SubscriptionManager(context = androidContext()) }
    viewModel { HomeViewModel(subscriptionManager = get()) }
}