package com.stlmkvd.smartway.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.stlmkvd.smartway.network.AuthInterceptor
import com.stlmkvd.smartway.network.UnisplashApi
import com.stlmkvd.smartway.network.calladapters.ResultCallAdapter
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

private const val BASE_URL = "https://api.unsplash.com/"
private const val ACCESS_KEY = "DTJCh0JZJ3e5iz0iqmrSkHth49rALyiBtyUPge3RPR8"

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideBaseUrl(): HttpUrl {
        return BASE_URL.toHttpUrl()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        baseUrl: HttpUrl,
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        adapterFactory: ResultCallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(adapterFactory)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideApi(
        retrofit: Retrofit
    ): UnisplashApi {
        return retrofit.create(UnisplashApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideCoroutineContext(): CoroutineContext {
        return (Dispatchers.IO + SupervisorJob())
    }
}