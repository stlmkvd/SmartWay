package com.stlmkvd.smartway.imagegallery.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class Photo(
    val id: String,
    val url: String
)