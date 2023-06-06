package com.stlmkvd.smartway.imagegallery.di.modules

import androidx.lifecycle.ViewModel
import com.stlmkvd.smartway.core.di.ViewModelKey
import com.stlmkvd.smartway.imagegallery.ui.viewmodel.ImageGalleryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        UiModule.Bindings::class
    ]
)
class UiModule {

    @Module
    interface Bindings {

        @[Binds IntoMap]
        @ViewModelKey(ImageGalleryViewModel::class)
        fun bind(vm: ImageGalleryViewModel): ViewModel
    }
}