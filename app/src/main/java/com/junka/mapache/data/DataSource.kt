package com.junka.mapache.data

import com.junka.mapache.data.local.LocalDataSource
import com.junka.mapache.data.model.*
import com.junka.mapache.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataSource(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    val animes = localDataSource.getAnime().map { it.toBusinessModelList() }

    suspend fun requestAnime() = withContext(Dispatchers.IO) {
        if (localDataSource.isEmpty()){
            val animes = remoteDataSource.getAnimes().data.documents.toLocalModelList()
            localDataSource.insert(animes)
        }
    }

    fun getAnimeById(id : Int): Flow<Anime> = localDataSource.getAnimeById(id).map { it.toBusiness() }
}