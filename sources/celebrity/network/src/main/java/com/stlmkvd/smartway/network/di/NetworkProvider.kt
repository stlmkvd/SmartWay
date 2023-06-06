package com.stlmkvd.smartway.network.di

import com.google.gson.Gson
import com.stlmkvd.smartway.network.AuthInterceptor
import com.stlmkvd.smartway.network.UnisplashApi
import okhttp3.OkHttpClient
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

interface NetworkProvider {

    fun unisplashApi(): UnisplashApi

    fun coroutineContext(): CoroutineContext

    fun gson(): Gson

    @Named("main")
    fun okHttpClient(): OkHttpClient

    fun authInterceptor(): AuthInterceptor
}