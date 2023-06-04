package com.stlmkvd.smartway.network

import android.graphics.Bitmap
import com.stlmkvd.smartway.network.model.response.ListPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnisplashApi {

    @GET("photos")
    fun listPhotos(
        @Query("page") page: Int? = null,
        @Query("per_page") pageSize: Int? = null,
        @Query("order_by") orderBy: String? = null
    ): Result<ListPhotosResponse>

    companion object Order {
        const val LATEST = "latest"
        const val OLDEST = "oldest"
        const val POPULAR = "popular"
    }
}