package com.junka.mapache.domain.useCase

import com.junka.mapache.data.model.Document
import com.junka.mapache.domain.repository.AnimeRepository

class AnimeListUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend operator fun invoke() : List<Document> {
        return animeRepository.getAnimes()
    }
}