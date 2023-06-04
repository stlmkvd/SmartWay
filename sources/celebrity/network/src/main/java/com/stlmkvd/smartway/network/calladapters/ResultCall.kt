package com.stlmkvd.smartway.network.calladapters

import com.stlmkvd.smartway.network.mapper.ResponseMapper
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ResultCall<T : Any>(
    private val proxy: Call<T>,
    private val responseMapper: ResponseMapper,
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = responseMapper.mapToResult(response)
                callback.onResponse(this@ResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = Result.failure<T>(t)
                callback.onResponse(this@ResultCall, Response.success(result))
            }
        })
    }

    override fun execute(): Response<Result<T>> {
        val result = responseMapper.mapToResult(proxy.execute())
        return Response.success(result)
    }

    override fun clone(): Call<Result<T>> = ResultCall(proxy.clone(), responseMapper)
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun cancel() = proxy.cancel()
}