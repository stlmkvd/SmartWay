package com.stlmkvd.smartway.network.mapper

import com.google.gson.Gson
import com.stlmkvd.smartway.network.model.ErrorResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ResponseMapper
@Inject constructor(
    private val gson: Gson,
    private val errorsMapper: ApiErrorMapper,
    private val responseCodeMapper: ResponseCodeMapper
) {

    fun <T : Any> mapToResult(response: Response<T>): Result<T> {
        val body = response.body()
        return if (response.isSuccessful) {
            Result.success(body!!)
        } else {
            val errors = response.errorBody()
                ?.let {
                    gson.fromJson(it.toString(), ErrorResponse::class.java).errors
                }
                ?.map(errorsMapper::map)
            Result.failure(responseCodeMapper.mapToException(response.code(), errors))
        }
    }
}