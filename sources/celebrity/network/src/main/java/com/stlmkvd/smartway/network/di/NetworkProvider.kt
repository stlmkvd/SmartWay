package com.stlmkvd.smartway.network.di

import com.stlmkvd.smartway.network.UnisplashApi

interface NetworkProvider {

    fun unisplashApi(): UnisplashApi
}