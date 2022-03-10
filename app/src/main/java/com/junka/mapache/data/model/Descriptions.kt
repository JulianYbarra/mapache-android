package com.junka.mapache.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Descriptions(
    @SerializedName("en")
    val en: String,
    @SerializedName("fr")
    val fr: String?,
    @SerializedName("it")
    val it: String?,
    @SerializedName("jp")
    val jp: String?
) : Parcelable