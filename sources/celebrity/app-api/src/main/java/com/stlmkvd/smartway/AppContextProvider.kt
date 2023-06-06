package com.stlmkvd.smartway

import android.content.Context

interface AppContextProvider {

    fun provideContext(): Context
}