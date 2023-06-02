package com.rg.capstone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RiseGoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
//        if(BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }
    }
}