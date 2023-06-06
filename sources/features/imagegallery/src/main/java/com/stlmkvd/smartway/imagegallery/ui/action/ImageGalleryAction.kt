package com.stlmkvd.smartway.imagegallery.ui.action

import com.stlmkvd.smartway.core.ui.MviAction
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo

sealed interface ImageGalleryAction : MviAction {

    data class NewPhotosLoadedAction(val photos: List<Photo>) : ImageGalleryAction

    data class PhotoDownloadFailed(val throwable: Throwable) : ImageGalleryAction

    object StartLoading : ImageGalleryAction
}