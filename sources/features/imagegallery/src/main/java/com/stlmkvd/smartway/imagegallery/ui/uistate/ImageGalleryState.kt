package com.stlmkvd.smartway.imagegallery.ui.uistate

import androidx.compose.runtime.Immutable
import com.stlmkvd.smartway.core.ui.MviState
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo

@Immutable
data class ImageGalleryState(
    val photos: List<Photo> = listOf(),
    val isLoading: Boolean = false
) : MviState