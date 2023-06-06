package com.stlmkvd.smartway.imagegallery

import androidx.compose.runtime.Composable
import com.stlmkvd.imagegalleryapi.ImageGalleryMediator
import com.stlmkvd.smartway.imagegallery.ui.ImageGalleryView
import javax.inject.Inject

class ImageGalleryMediatorImpl
@Inject constructor() : ImageGalleryMediator {

    @Composable override fun Entry() {
        ImageGalleryView().Entry()
    }
}