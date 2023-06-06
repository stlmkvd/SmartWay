package com.stlmkvd.smartway.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id")
    val id: String,
    val links: JsonObject
)