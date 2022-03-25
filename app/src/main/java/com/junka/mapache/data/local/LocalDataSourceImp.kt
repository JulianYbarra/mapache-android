package com.junka.mapache.data.local

import androidx.paging.PagingSource
import androidx.room.RoomDatabase
import com.junka.mapache.data.local.dao.AnimeDao
import com.junka.mapache.data.local.model.Anime
import com.junka.mapache.data.local.model.RemoteKeys
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImp(
    private val mapacheDataBase: MapacheDataBase,
    private val dao: AnimeDao
) : LocalDataSource {

    override val database: RoomDatabase
        get() = mapacheDataBase

    override suspend fun isEmpty(): Boolean = dao.count() == 0
    override fun getAnime(): Flow<List<Anime>> = dao.getAll()
    override fun getAnimeById(id: Int): Flow<Anime> = dao.findById(id)
    override suspend fun insert(animes: List<Anime>) = dao.insert(animes)
    override suspend fun update(anime: Anime) = dao.update(anime)

    override fun animeByTitle(title: String): PagingSource<Int, Anime> {
        val query = "%${title}%"
        return dao.animeByTitle(query)
    }
    override suspend fun clearAnime() = dao.clear()


    override suspend fun remoteKeysAnimeId(id: Int): RemoteKeys? = mapacheDataBase.remoteKeyDao().remoteKeysAnimeId(id)
    override suspend fun clearKeys() = mapacheDataBase.remoteKeyDao().clear()
    override suspend fun insertKey(keys: List<RemoteKeys>) = mapacheDataBase.remoteKeyDao().insert(keys)

}