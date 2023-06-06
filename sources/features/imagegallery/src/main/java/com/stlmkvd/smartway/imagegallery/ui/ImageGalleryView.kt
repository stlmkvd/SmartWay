package com.stlmkvd.smartway.imagegallery.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.stlmkvd.smartway.BaseApp
import com.stlmkvd.smartway.core.MultiViewModelFactory
import com.stlmkvd.smartway.imagegallery.di.ImageGalleryComponent
import com.stlmkvd.smartway.imagegallery.domain.entity.Photo
import com.stlmkvd.smartway.imagegallery.ui.event.ImageGalleryEvent
import com.stlmkvd.smartway.imagegallery.ui.uistate.ImageGalleryState
import com.stlmkvd.smartway.imagegallery.ui.viewmodel.ImageGalleryViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named

private const val LOADING_THRESHOLD = 10

class ImageGalleryView {

    private val localViewModelAmbient =
        staticCompositionLocalOf<ImageGalleryViewModel> { error("No ViewModelFactory provided") }

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    @Named("coil")
    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Composable
    fun Entry() {
        val appProvider = (LocalContext.current.applicationContext as BaseApp).getApplicationProvider()
        val component = remember { ImageGalleryComponent.init(appProvider) }
        component.inject(this)

        val vm: ImageGalleryViewModel = viewModel(factory = viewModelFactory) as ImageGalleryViewModel

        CompositionLocalProvider(localViewModelAmbient provides vm) {
            val state by vm.uiState.collectAsState()
            if (state.photos.isEmpty()) {
                vm.acceptUiIntent(ImageGalleryEvent.LoadNextPhotos)
            }
            ImageGallery(state)
        }
    }

    @Composable
    private fun ImageGallery(state: ImageGalleryState) {
        PhotoGrid(photos = state.photos.toImmutableList())
    }

    @Composable
    fun PhotoGrid(photos: ImmutableList<Photo>) {
        val columnCount = 3


        LazyVerticalGrid(
            columns = GridCells.Fixed(count = columnCount),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(
                photos,
                key = { _, photo ->
                    photo.id
                }
            ) { idx, photo ->
                val vm = localViewModelAmbient.current
                Log.d("cview", "key idx = $idx")
                if (LOADING_THRESHOLD >= photos.size - idx) {
                    vm.acceptUiIntent(ImageGalleryEvent.LoadNextPhotos)
                }
                PhotoView(photo)
            }
        }
    }

    @Composable
    fun PhotoView(photo: Photo) {
        Image(
            painter = rememberImagePainter(
                data = photo.url,
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .okHttpClient(okHttpClient)
                    .build()
            ),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1f)
                .background(Color(Color.Black.toArgb()))
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}
