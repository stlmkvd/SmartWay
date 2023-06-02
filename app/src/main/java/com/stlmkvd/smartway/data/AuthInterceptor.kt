package com.stlmkvd.smartway.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val AUTH_PARAMETER = "Authorization"
private const val ACCESS_TOKEN = "DTJCh0JZJ3e5iz0iqmrSkHth49rALyiBtyUPge3RPR8"

class AuthInterceptor
@Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header(AUTH_PARAMETER, ACCESS_TOKEN)
            .build()
        return chain.proceed(request)
    }
}