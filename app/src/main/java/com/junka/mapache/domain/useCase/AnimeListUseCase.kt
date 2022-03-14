package com.junka.mapache.domain.useCase

import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeListUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(): Flow<List<Anime>> = animeRepository.getAnimes()

}