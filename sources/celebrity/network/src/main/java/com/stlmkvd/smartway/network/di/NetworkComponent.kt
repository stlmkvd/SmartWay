package com.stlmkvd.smartway.network.di

import com.stlmkvd.smartway.network.calladapters.ResultCallAdapter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
abstract class NetworkComponent : NetworkProvider {

    internal abstract fun inject(adapter: ResultCallAdapter)

    companion object Holder {

        var INSTANCE: NetworkComponent = DaggerNetworkComponent.create()
    }
}