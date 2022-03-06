package com.junka.mapache.data.model


import com.google.gson.annotations.SerializedName

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
)