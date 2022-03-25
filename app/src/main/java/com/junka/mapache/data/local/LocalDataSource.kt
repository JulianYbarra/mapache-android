package com.junka.mapache.data.local

import androidx.paging.PagingSource
import androidx.room.RoomDatabase
import com.junka.mapache.data.local.model.Anime
import com.junka.mapache.data.local.model.RemoteKeys
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val database : RoomDatabase

    suspend fun isEmpty() : Boolean
    fun getAnime () : Flow<List<Anime>>
    fun getAnimeById(id : Int) : Flow<Anime>
    suspend fun insert(animes : List<Anime>)
    suspend fun update(anime : Anime)
    fun animeByTitle(title: String): PagingSource<Int, Anime>

    suspend fun remoteKeysAnimeId(id : Int) : RemoteKeys?

    suspend fun clearKeys()
    suspend fun clearAnime()
    suspend fun insertKey(keys: List<RemoteKeys>)
}