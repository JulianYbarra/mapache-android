package com.junka.mapache.domain.model

import com.junka.mapache.data.remote.model.Document
import com.junka.mapache.data.remote.model.toDescription
import com.junka.mapache.data.remote.model.toTitle
import  com.junka.mapache.data.local.model.Anime as AnimeLocal


fun Anime.toLocalModel() = AnimeLocal(id, bannerImage, coverImage, description, title, endDate, episodeDuration, episodesCount, format, nsfw, score, seasonPeriod, seasonYear, startDate, status, trailerUrl, weeklyAiringDay)

fun Document.toLocalModel() = AnimeLocal(id,bannerImage ?: "",coverImage,descriptions.toDescription(),titles.toTitle(),endDate,episodeDuration,episodesCount, format, nsfw, score, seasonPeriod, seasonYear, startDate, status, trailerUrl, weeklyAiringDay)

fun List<Document>.toLocalModelList() = map { it.toLocalModel() }

fun AnimeLocal.toDomainModel() = Anime(id, bannerImage, coverImage, descriptions, title, endDate, episodeDuration, episodesCount, format, nsfw, score, seasonPeriod, seasonYear, startDate, status, trailerUrl, weeklyAiringDay)

fun List<AnimeLocal>.toDomainModel() = map { it.toDomainModel() }