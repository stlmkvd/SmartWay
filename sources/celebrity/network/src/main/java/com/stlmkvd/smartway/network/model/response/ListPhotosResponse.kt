package com.stlmkvd.smartway.network.model.response

data class ListPhotosResponse(
    val id: String,
    val urls: Map<String, String>
)