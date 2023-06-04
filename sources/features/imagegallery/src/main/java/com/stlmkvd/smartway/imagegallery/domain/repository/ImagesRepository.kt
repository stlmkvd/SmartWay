package com.stlmkvd.smartway.imagegallery.domain.repository

import com.stlmkvd.smartway.imagegallery.domain.entity.PagingOptions
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo

interface ImagesRepository {

    suspend fun fetchPhotos(options: PagingOptions): Result<List<Photo>>
}