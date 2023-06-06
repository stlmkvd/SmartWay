package com.stlmkvd.smartway.network.calladapters

import com.stlmkvd.smartway.network.mapper.ResponseMapper
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

class ResultCallAdapter(
    private val resultType: Type,
    private val responseMapper: ResponseMapper
) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> {
        return ResultCall(call, responseMapper)
    }

    @Singleton
    class Factory
    @Inject constructor(
        private val responseMapper: ResponseMapper
    ) : CallAdapter.Factory() {

        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            if (getRawType(callType) != Result::class.java) {
                return null
            }

            val resultType = getParameterUpperBound(0, callType as ParameterizedType)
            return ResultCallAdapter(resultType, responseMapper)
        }
    }
}