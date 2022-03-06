package com.junka.mapache.data.model


import com.google.gson.annotations.SerializedName

data class Descriptions(
    @SerializedName("en")
    val en: String,
    @SerializedName("fr")
    val fr: String,
    @SerializedName("it")
    val it: String,
    @SerializedName("jp")
    val jp: String
)