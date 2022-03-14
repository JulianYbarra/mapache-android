package com.junka.mapache.domain.repository

import com.junka.mapache.data.DataSource

class AnimeRepository(
    private val dataSource: DataSource
) {

    fun getAnimes() = dataSource.animes
    fun getAnimeById(id : Int) = dataSource.getAnimeById(id)

    suspend fun refreshAnimes() = dataSource.requestAnime()
}