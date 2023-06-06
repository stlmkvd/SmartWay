package com.stlmkvd.smartway.imagegallery.ui.event

import com.stlmkvd.smartway.core.ui.MviEvent

sealed interface ImageGalleryEvent : MviEvent {

    object LoadNextPhotos : ImageGalleryEvent
}