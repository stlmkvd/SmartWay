package com.stlmkvd.smartway.imagegallery.di.modules

import android.content.Context
import coil.util.CoilUtils
import com.stlmkvd.smartway.core.di.FeatureScope
import com.stlmkvd.smartway.imagegallery.data.ImageRepositoryImpl
import com.stlmkvd.smartway.imagegallery.domain.repository.ImagesRepository
import com.stlmkvd.smartway.network.AuthInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module(
    includes = [
        DataModule.Bindings::class
    ]
)
class DataModule {

    @FeatureScope
    @Provides
    @Named("coil")
    fun provideCoilClient(
        interceptor: AuthInterceptor,
        context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(CoilUtils.createDefaultCache(context))
            .addInterceptor(interceptor)
            .build()
    }

    @Module
    interface Bindings {

        @Binds
        fun bindRepositoryImpl(repo: ImageRepositoryImpl): ImagesRepository
    }
}