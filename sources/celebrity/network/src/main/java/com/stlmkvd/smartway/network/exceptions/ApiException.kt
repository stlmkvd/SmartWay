package com.stlmkvd.smartway.network.exceptions

import com.stlmkvd.smartway.network.model.ApiError

sealed class ApiException(val body: List<ApiError>?) : RuntimeException() {

    class ForbiddenException(body: List<ApiError>?) : ApiException(body)

    class UnexpectedApiException(body: List<ApiError>?) : ApiException(body)
}