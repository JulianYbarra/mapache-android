package com.junka.mapache.domain.repository

import com.junka.mapache.data.DataSource

class AnimeRepository(
    private val dataSource: DataSource
) {
    fun getAnimes() = dataSource.animes
    suspend fun refreshAnimes() = dataSource.requestAnime()
}