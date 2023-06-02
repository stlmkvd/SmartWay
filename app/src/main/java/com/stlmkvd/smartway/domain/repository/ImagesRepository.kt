package com.stlmkvd.smartway.domain.repository

interface ImagesRepository<Options, Images> {

    fun getImages(options: Options): Images
}