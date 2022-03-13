package com.junka.mapache.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junka.mapache.data.local.model.Anime

@Database(entities = [Anime::class], version = 1, exportSchema = false)
abstract class MapacheDataBase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}