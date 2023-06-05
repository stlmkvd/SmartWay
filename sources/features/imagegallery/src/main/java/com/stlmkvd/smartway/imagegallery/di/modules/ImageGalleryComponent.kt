package com.stlmkvd.smartway.imagegallery.di.modules

import com.stlmkvd.smartway.core.di.FeatureScope
import com.stlmkvd.smartway.network.di.NetworkComponent
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        NetworkComponent::class
    ]
)
interface ImageGalleryComponent {

    @Component.Factory
    interface Factory {

        fun create(networkComponent: NetworkComponent): ImageGalleryComponent
    }
}