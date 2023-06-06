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
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

private const val BASE_URL = "https://api.unsplash.com/"
private const val ACCESS_KEY = "kSZN0jzrxX8_GdnCzO7fSdb2Yh-6Mv84RJFh01hXUmM"

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): HttpUrl {
        return BASE_URL.toHttpUrl()
    }

    @Provides
    @Singleton
    @Named("main")
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: HttpUrl,
        @Named("main")
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
    fun provideApi(
        retrofit: Retrofit
    ): UnisplashApi {
        return retrofit.create(UnisplashApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson,
    ): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return (Dispatchers.IO + SupervisorJob())
    }
}