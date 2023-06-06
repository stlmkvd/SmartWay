package com.stlmkvd.smartway.imagegallery.data

import com.google.gson.Gson
import com.stlmkvd.smartway.core.di.FeatureScope
import com.stlmkvd.smartway.imagegallery.domain.entity.PagingOptions
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo
import com.stlmkvd.smartway.imagegallery.domain.repository.ImagesRepository
import com.stlmkvd.smartway.network.UnisplashApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@FeatureScope
class ImageRepositoryImpl
@Inject constructor(
    private val api: UnisplashApi,
    private val context: CoroutineContext,
    private val gson: Gson
) : ImagesRepository {

    override suspend fun fetchPhotos(options: PagingOptions): Result<List<Photo>> {
        return withContext(context) {
            api.listPhotos(options.pageNum, options.pageSize)
                .map { photos ->
                    photos.map { photoDto ->
                        Photo(
                            photoDto.id,
                            photoDto.links.get("download").asString
                        )
                    }
                }
        }

//    private fun CoroutineScope.fetchMapPhoto(photoDto: PhotoDto): Deferred<Result<Photo>> {
//        return async(context) {
//            val id = photoDto.id
//            api.getPhotoDownloadUrl(id).map { downloadUrlResponse ->
//                Photo(id, downloadUrlResponse.url)
//            }
//        }
//    }
    }
}