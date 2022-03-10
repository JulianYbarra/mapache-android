package com.junka.mapache.di

import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.domain.useCase.AnimeListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesAnimeListUseCase(animeRepository: AnimeRepository): AnimeListUseCase =
        AnimeListUseCase(animeRepository)
}