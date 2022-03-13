package com.junka.mapache.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anime(
    @PrimaryKey val id: Int,
    val bannerImage: String,
    val coverImage: String,
    val descriptions: String,
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
)