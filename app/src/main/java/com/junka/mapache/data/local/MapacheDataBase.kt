package com.junka.mapache.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junka.mapache.data.local.dao.AnimeDao
import com.junka.mapache.data.local.dao.RemoteKeyDao
import com.junka.mapache.data.local.model.Anime
import com.junka.mapache.data.local.model.RemoteKeys

@Database(entities = [Anime::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class MapacheDataBase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun remoteKeyDao() : RemoteKeyDao
}