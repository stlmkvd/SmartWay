package com.stlmkvd.smartway.network.mapper

import com.stlmkvd.smartway.network.model.ApiError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApiErrorMapper
@Inject constructor(){

    fun map(errorStr: String): ApiError {
        return when (errorStr) {
            "OAuth error: The access token is invalid" -> {
                ApiError.ACCESS_TOKEN_INVALID
            }

            else -> ApiError.UNKNOWN_ERROR
        }
    }
}