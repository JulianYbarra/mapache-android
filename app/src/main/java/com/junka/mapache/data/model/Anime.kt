package com.junka.mapache.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
    val id: Int,
    val bannerImage: String,
    val coverImage: String,
    val description: String,
    val title : String,
    val endDate: String?,
    val episodeDuration: Int,
    val episodesCount: Int,
    val format: Int,
    val nsfw: Boolean,
    val score: Int,
    val seasonPeriod: Int,
    val seasonYear: Int,
    val startDate: String?,
    val status: Int,
    val trailerUrl: String?,
    val weeklyAiringDay: Int
) : Parcelable
