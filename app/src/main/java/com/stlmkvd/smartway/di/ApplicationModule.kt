package com.stlmkvd.smartway.di

import com.stlmkvd.smartway.imagegallery.di.modules.ImageGalleryMediatorModule
import com.stlmkvd.smartway.network.di.NetworkModule
import dagger.Module

@Module(
    includes = [
        ApplicationModule.MediatorsModule::class,
        NetworkModule::class
    ]
)
class ApplicationModule {

    @Module(
        includes = [
            ImageGalleryMediatorModule::class
        ]
    )
    class MediatorsModule
}