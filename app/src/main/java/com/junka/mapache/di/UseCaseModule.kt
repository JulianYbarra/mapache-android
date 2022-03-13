package com.junka.mapache.di

import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.domain.useCase.AnimeListUseCase
import com.junka.mapache.domain.useCase.RefreshAnimeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesAnimeListUseCase(animeRepository: AnimeRepository) = AnimeListUseCase(animeRepository)

    @Provides
    fun providesRefreshAnimeListUseCase(animeRepository: AnimeRepository) = RefreshAnimeListUseCase(animeRepository)
}