package com.stlmkvd.smartway.network.mapper

import com.stlmkvd.smartway.network.exceptions.ApiException
import com.stlmkvd.smartway.network.model.ApiError
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResponseCodeMapper
@Inject constructor() {

    fun mapToException(httpCode: Int, errors: List<ApiError>? = null): ApiException {
        return when (httpCode) {
            HttpURLConnection.HTTP_FORBIDDEN -> {
                ApiException.ForbiddenException(errors)
            }

            else -> {
                ApiException.UnexpectedApiException(errors)
            }
        }
    }
}