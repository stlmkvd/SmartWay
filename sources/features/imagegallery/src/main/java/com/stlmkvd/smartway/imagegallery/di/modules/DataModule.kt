package com.stlmkvd.smartway.imagegallery.di.modules

import com.stlmkvd.smartway.imagegallery.data.ImageRepositoryImpl
import com.stlmkvd.smartway.imagegallery.domain.repository.ImagesRepository
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DataModule.Bindings::class
    ]
)
object DataModule {

    @Module
    interface Bindings {

        @Binds
        fun bindRepositoryImpl(repo: ImageRepositoryImpl): ImagesRepository
    }
}