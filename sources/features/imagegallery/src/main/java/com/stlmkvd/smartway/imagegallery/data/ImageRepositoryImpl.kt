package com.stlmkvd.smartway.imagegallery.data

import com.stlmkvd.smartway.imagegallery.domain.entity.PagingOptions
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo
import com.stlmkvd.smartway.imagegallery.domain.repository.ImagesRepository
import com.stlmkvd.smartway.network.UnisplashApi
import com.stlmkvd.smartway.network.model.PhotoDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ImageRepositoryImpl
@Inject constructor(
    private val api: UnisplashApi,
    private val context: CoroutineContext
) : ImagesRepository {

    override suspend fun fetchPhotos(options: PagingOptions): Result<List<Photo>> {
        return withContext(context) {
            api.listPhotos(options.pageNum, options.pageSize)
                .getOrElse { return@withContext Result.failure(it) }
                .photoDtos
                .map { photoDto ->
                    fetchMapPhoto(photoDto)
                }
                .map {
                    it.await().getOrElse { err ->
                        return@withContext Result.failure(err)
                    }
                }.let {
                    Result.success(it)
                }
        }
    }

    private fun CoroutineScope.fetchMapPhoto(photoDto: PhotoDto): Deferred<Result<Photo>> {
        return async(context) {
            val id = photoDto.id
            api.getPhotoDownloadUrl(id).map { downloadUrlResponse ->
                Photo(UUID.fromString(id), URL(downloadUrlResponse.url))
            }
        }
    }
}