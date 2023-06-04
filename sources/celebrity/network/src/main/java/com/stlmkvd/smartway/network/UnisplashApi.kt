package com.stlmkvd.smartway.network

import com.stlmkvd.smartway.network.model.GetDownloadUrlResponse
import com.stlmkvd.smartway.network.model.ListPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnisplashApi {

    @GET("photos")
    suspend fun listPhotos(
        @Query("page") page: Int? = null,
        @Query("per_page") pageSize: Int? = null,
        @Query("order_by") orderBy: String? = null
    ): Result<ListPhotosResponse>

    @GET("photos/:id/download")
    suspend fun getPhotoDownloadUrl(@Path("id") id: String): Result<GetDownloadUrlResponse>

    companion object OrderBy {
        const val LATEST = "latest"
        const val OLDEST = "oldest"
        const val POPULAR = "popular"
    }
}