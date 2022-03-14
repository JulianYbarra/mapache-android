package com.junka.mapache.domain.useCase

import com.junka.mapache.domain.repository.AnimeRepository

class RefreshAnimeListUseCase(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke() = animeRepository.refreshAnimes()
}
