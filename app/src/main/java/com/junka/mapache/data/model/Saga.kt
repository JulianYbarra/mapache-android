package com.junka.mapache.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Saga(
    @SerializedName("descriptions")
    val descriptions: Descriptions,
    @SerializedName("episode_from")
    val episodeFrom: Int,
    @SerializedName("episode_to")
    val episodeTo: Int,
    @SerializedName("episodes_count")
    val episodesCount: Int,
    @SerializedName("titles")
    val titles: Titles
) : Parcelable