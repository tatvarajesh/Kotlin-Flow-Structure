package com.example.demokotlinflow

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.internal.Contexts.getApplication


@HiltAndroidApp
class MyApp:Application() {
    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCenter.start(
            getApplication(instance), "df0031e7-89b8-4841-82b6-31547cd6a6b5",
            Analytics::class.java, Crashes::class.java
        )
    }
}