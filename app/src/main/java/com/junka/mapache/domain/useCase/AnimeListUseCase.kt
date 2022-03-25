package com.junka.mapache.domain.useCase

import androidx.paging.PagingData
import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeListUseCase(
    private val animeRepository: AnimeRepository
) {
    operator fun invoke(title : String): Flow<PagingData<Anime>> = animeRepository.getAnime(title)

}