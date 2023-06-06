package com.stlmkvd.smartway.imagegallery.domain.usecase

import com.stlmkvd.smartway.core.domain.SuspendingUseCase
import com.stlmkvd.smartway.imagegallery.domain.entity.PagingOptions
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo
import com.stlmkvd.smartway.imagegallery.domain.repository.ImagesRepository
import javax.inject.Inject

class GetImagesUseCase
@Inject constructor(
    private val imagesRepository: ImagesRepository
) : SuspendingUseCase<PagingOptions, Result<List<Photo>>> {

    override suspend fun execute(input: PagingOptions): Result<List<Photo>> {
        return imagesRepository.fetchPhotos(input)
    }
}