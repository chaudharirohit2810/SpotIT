package com.rohit2810.spotit

import android.app.Application
import timber.log.Timber

public class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}