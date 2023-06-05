package com.stlmkvd.smartway

import android.app.Application
import com.stlmkvd.smartway.di.AppComponent

class SmartWayApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.builder()
//            .networkComponent(DaggerNetworkComponent.create())
//            .build()
    }
}