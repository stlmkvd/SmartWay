package com.stlmkvd.smartway.imagegallery.domain.entity

import java.net.URL
import java.util.UUID

data class Photo(
    val id: UUID,
    val url: URL
)