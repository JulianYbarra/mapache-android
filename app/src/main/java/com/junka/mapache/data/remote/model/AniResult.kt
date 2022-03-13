package com.junka.mapache.data.remote.model


import com.google.gson.annotations.SerializedName

data class AniResult(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("version")
    val version: String
)