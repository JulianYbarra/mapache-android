package com.junka.mapache.di

import com.junka.mapache.data.DataSource
import com.junka.mapache.domain.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAnimeRepository(dataSource: DataSource) : AnimeRepository{
        return AnimeRepository(dataSource)
    }
}