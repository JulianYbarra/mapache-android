package com.junka.mapache.data.remote.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("last_page")
    val lastPage: Int
)