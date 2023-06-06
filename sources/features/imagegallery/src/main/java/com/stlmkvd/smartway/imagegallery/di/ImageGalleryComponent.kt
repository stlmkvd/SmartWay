package com.stlmkvd.smartway.imagegallery.di

import com.stlmkvd.smartway.ApplicationProvider
import com.stlmkvd.smartway.core.di.FeatureScope
import com.stlmkvd.smartway.imagegallery.di.modules.DataModule
import com.stlmkvd.smartway.imagegallery.di.modules.ImageGalleryMediatorModule
import com.stlmkvd.smartway.imagegallery.di.modules.UiModule
import com.stlmkvd.smartway.imagegallery.ui.ImageGalleryView
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        ApplicationProvider::class
    ],
    modules = [
        ImageGalleryMediatorModule::class,
        UiModule::class,
        DataModule::class
    ]
)
interface ImageGalleryComponent {

    fun inject(v: ImageGalleryView)

    companion object {

        fun init(
            applicationProvider: ApplicationProvider,
        ): ImageGalleryComponent {
            return DaggerImageGalleryComponent.builder()
                .applicationProvider(applicationProvider)
                .build()
        }
    }

}