package com.stlmkvd.smartway.di

import android.content.Context
import com.stlmkvd.smartway.ApplicationProvider
import com.stlmkvd.smartway.network.di.NetworkModule
import com.stlmkvd.smartway.network.di.NetworkProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class
    ]
)
interface AppComponent :
    ApplicationProvider,
    NetworkProvider {

        @Component.Builder
        interface Builder {

            fun build(): AppComponent

            @BindsInstance
            fun appContext(c: Context): Builder
        }
    }