package com.junka.mapache.data

import androidx.paging.*
import com.junka.mapache.common.Error
import com.junka.mapache.common.tryCall
import com.junka.mapache.data.local.LocalDataSource
import com.junka.mapache.data.model.*
import com.junka.mapache.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataSource(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    private val NETWORK_PAGE_SIZE = 30

    val animes = localDataSource.getAnime().map { it.toBusinessModelList() }

    suspend fun requestAnime() : Error? = tryCall {
        if (localDataSource.isEmpty()){
            val animes = remoteDataSource.getAnimes().data.documents.toLocalModelList()
            localDataSource.insert(animes)
        }
    }

    @OptIn(ExperimentalPagingApi::class)
    fun getSearchResultStream(query: String): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = AnimeRemoteMediator(
                query,
                remoteDataSource,
                localDataSource
            ),
            pagingSourceFactory = { localDataSource.animeByTitle(query) }
        ).flow.map {
            it.map { it.toBusiness() }
        }
    }



    fun getAnimeById(id : Int): Flow<Anime> = localDataSource.getAnimeById(id).map { it.toBusiness() }
}