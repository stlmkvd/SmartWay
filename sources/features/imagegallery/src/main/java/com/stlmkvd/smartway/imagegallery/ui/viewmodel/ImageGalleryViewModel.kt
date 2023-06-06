package com.stlmkvd.smartway.imagegallery.ui.viewmodel

import com.stlmkvd.smartway.core.di.FeatureScope
import com.stlmkvd.smartway.core.ui.BaseViewModel
import com.stlmkvd.smartway.imagegallery.domain.entity.PagingOptions
import com.stlmkvd.smartway.imagegallery.domain.usecase.GetImagesUseCase
import com.stlmkvd.smartway.imagegallery.ui.action.ImageGalleryAction
import com.stlmkvd.smartway.imagegallery.ui.event.ImageGalleryEvent
import com.stlmkvd.smartway.imagegallery.ui.uistate.ImageGalleryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

const val PAGE_SIZE = 30

@FeatureScope
class ImageGalleryViewModel
@Inject constructor(
    private val useCase: GetImagesUseCase
) : BaseViewModel<
    ImageGalleryEvent,
    ImageGalleryState,
    ImageGalleryAction
    >(ImageGalleryState()) {

    private val pagesLoaded = AtomicInteger(1)

    override fun processUiIntent(uiIntent: ImageGalleryEvent): Flow<ImageGalleryAction> {
        return when (uiIntent) {
            is ImageGalleryEvent.LoadNextPhotos -> onLoadNextItemsIntent()
        }
    }

    override fun reduceState(state: ImageGalleryState, action: ImageGalleryAction): ImageGalleryState {
        return when (action) {
            is ImageGalleryAction.NewPhotosLoadedAction -> {
                state.copy(
                    photos = state.photos + action.photos,
                    isLoading = false
                )
            }

            is ImageGalleryAction.PhotoDownloadFailed -> {
                state.copy(
                    isLoading = false
                )
            }

            is ImageGalleryAction.StartLoading -> {
                state.copy(
                    isLoading = true
                )
            }
        }
    }

    private fun onLoadNextItemsIntent(): Flow<ImageGalleryAction> {
        return flow {
            if (uiState.value.isLoading) return@flow
            emit(ImageGalleryAction.StartLoading)
            useCase.execute(PagingOptions(pagesLoaded.get() + 1, PAGE_SIZE)).onSuccess {
                pagesLoaded.incrementAndGet()
                emit(ImageGalleryAction.NewPhotosLoadedAction(it))
            }.onFailure {
                emit(ImageGalleryAction.PhotoDownloadFailed(it))
            }
        }
    }
}