package com.junka.mapache.di

import com.junka.mapache.data.DataSource
import com.junka.mapache.data.local.dao.AnimeDao
import com.junka.mapache.data.local.LocalDataSource
import com.junka.mapache.data.local.LocalDataSourceImp
import com.junka.mapache.data.local.MapacheDataBase
import com.junka.mapache.data.remote.AniService
import com.junka.mapache.data.remote.RemoteDataSource
import com.junka.mapache.data.remote.RemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(localDataSource : LocalDataSource,
                          remoteDataSource: RemoteDataSource) : DataSource{
        return DataSource(localDataSource,remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: AniService) : RemoteDataSource{
        return RemoteDataSourceImp(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(database : MapacheDataBase,animeDao: AnimeDao) : LocalDataSource{
        return LocalDataSourceImp(database,animeDao)
    }
}