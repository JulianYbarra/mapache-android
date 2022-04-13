package com.junka.mapache.domain.useCase

import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.common.Error

class RefreshAnimeListUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke() : Error? = animeRepository.refreshAnimes()
}
