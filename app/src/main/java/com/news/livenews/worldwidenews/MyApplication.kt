package com.news.livenews.worldwidenews

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.news.livenews.worldwidenews.Utils.AppOpenManager
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {
    private var appOpenManager: AppOpenManager? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(
            this
        ) { }

        appOpenManager = AppOpenManager(this)
    }
}