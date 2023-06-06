package com.stlmkvd.smartway.imagegallery.di.modules

import com.stlmkvd.imagegalleryapi.ImageGalleryMediator
import com.stlmkvd.smartway.core.MediatorsQualifier
import com.stlmkvd.smartway.imagegallery.ImageGalleryMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ImageGalleryMediatorModule {

    @[Binds IntoMap]
    @MediatorsQualifier
    @ClassKey(ImageGalleryMediator::class)
    fun bindMediator(mediator: ImageGalleryMediatorImpl): Any
}