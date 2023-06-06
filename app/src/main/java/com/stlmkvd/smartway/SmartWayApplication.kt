package com.stlmkvd.smartway

import android.app.Application
import com.stlmkvd.smartway.di.AppComponent
import com.stlmkvd.smartway.di.DaggerAppComponent

class SmartWayApplication : Application(), BaseApp {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appContext(this).build()
    }

    override fun getApplicationProvider(): ApplicationProvider {
        return appComponent
    }
}