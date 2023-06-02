package com.stlmkvd.smartway.domain.usecase

import com.stlmkvd.smartway.domain.repository.ImagesRepository

class GetImagesUseCase<Options, Images>(
    private val imagesRepository: ImagesRepository<Options, Images>
) :
    UseCase<Options, Images> {

    override fun execute(input: Options): Images {
        return imagesRepository.getImages(input)
    }
}