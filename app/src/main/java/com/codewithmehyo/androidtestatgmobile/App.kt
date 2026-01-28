package com.codewithmehyo.androidtestatgmobile

import android.app.Application
import com.codewithmehyo.androidtestatgmobile.features.home.homeModule
import com.codewithmehyo.androidtestatgmobile.features.player.playerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(androidContext = this@App)
            modules(homeModule, playerModule)
        }
    }
}
