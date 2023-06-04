package com.stlmkvd.smartway.network.model

import com.google.gson.annotations.SerializedName

data class GetDownloadUrlResponse(
    @SerializedName("url")
    val url: String
)