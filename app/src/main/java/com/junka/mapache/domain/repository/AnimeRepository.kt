package com.junka.mapache.domain.repository

import com.junka.mapache.data.DataSource

class AnimeRepository(
    private val dataSource: DataSource
) {
    suspend fun getAnimes() = dataSource.getAnimes()
}