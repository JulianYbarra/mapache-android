package com.junka.mapache.domain.useCase

import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeDetailUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(id : Int) : Flow<Anime> {
        return animeRepository.getAnimeById(id)
    }
}