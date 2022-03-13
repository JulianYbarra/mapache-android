package com.junka.mapache.di

import android.app.Application
import androidx.room.Room
import com.junka.mapache.data.local.AnimeDao
import com.junka.mapache.data.local.MapacheDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): MapacheDataBase {
        return Room.databaseBuilder(
            application,
            MapacheDataBase::class.java, "mapache-db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesAnimeDao(database: MapacheDataBase): AnimeDao = database.animeDao()
}