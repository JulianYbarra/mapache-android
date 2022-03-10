package com.junka.mapache.di

import com.junka.mapache.data.DataSource
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
    fun provideDataSource(remoteDataSource: RemoteDataSource) : DataSource{
        return DataSource(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: AniService) : RemoteDataSource{
        return RemoteDataSourceImp(apiService)
    }
}