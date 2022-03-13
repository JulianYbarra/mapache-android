package com.junka.mapache.data.remote.model

import com.google.gson.annotations.SerializedName

data class Document(
    @SerializedName("anilist_id")
    val anilistId: Int,
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("cover_color")
    val coverColor: String?,
    @SerializedName("cover_image")
    val coverImage: String,
    @SerializedName("descriptions")
    val descriptions: Descriptions,
    @SerializedName("end_date")
    val endDate: String?,
    @SerializedName("episode_duration")
    val episodeDuration: Int,
    @SerializedName("episodes_count")
    val episodesCount: Int,
    @SerializedName("format")
    val format: Int,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("hasCoverImage")
    val hasCoverImage: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("nsfw")
    val nsfw: Boolean,
    @SerializedName("prequel")
    val prequel: Int,
    @SerializedName("sagas")
    val sagas: List<Saga>?,
    @SerializedName("score")
    val score: Int,
    @SerializedName("season_period")
    val seasonPeriod: Int,
    @SerializedName("season_year")
    val seasonYear: Int,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("status")
    val status: Int,
    @SerializedName("titles")
    val titles: Titles,
    @SerializedName("tmdb_id")
    val tmdbId: Int,
    @SerializedName("trailer_url")
    val trailerUrl: String?,
    @SerializedName("weekly_airing_day")
    val weeklyAiringDay: Int
)
