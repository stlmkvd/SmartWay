package com.stlmkvd.smartway

import com.stlmkvd.smartway.core.MediatorsProvider
import com.stlmkvd.smartway.network.di.NetworkProvider

interface ApplicationProvider :  MediatorsProvider, NetworkProvider, AppContextProvider