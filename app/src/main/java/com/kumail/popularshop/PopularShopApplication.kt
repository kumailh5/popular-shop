package com.kumail.popularshop

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by kumailhussain on 15/10/2021.
 */
@HiltAndroidApp
class PopularShopApplication : Application() {

    companion object {
        var deviceWidth = 0
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}