package com.junka.mapache.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.junka.mapache.data.local.LocalDataSource
import com.junka.mapache.data.local.model.RemoteKeys
import com.junka.mapache.data.local.model.Anime
import com.junka.mapache.data.model.toLocalModelList
import com.junka.mapache.data.remote.RemoteDataSource
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val query: String,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : RemoteMediator<Int, Anime>() {

    private val ANI_STARTING_PAGE_INDEX = 1

    override suspend fun initialize(): InitializeAction {
        // Launch remote refresh as soon as paging starts and do not trigger remote prepend or
        // append until refresh has succeeded. In cases where we don't mind showing out-of-date,
        // cached offline data, we can return SKIP_INITIAL_REFRESH instead to prevent paging
        // triggering remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Anime>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { anime ->
                // Get the remote keys of the last item retrieved
                localDataSource.remoteKeysAnimeId(anime.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Anime>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                // Get the remote keys of the first items retrieved
                localDataSource.remoteKeysAnimeId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Anime>
    ): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { animeId ->
                localDataSource.remoteKeysAnimeId(animeId)
            }
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Anime>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: ANI_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        val apiQuery = query

        try {
            val apiResponse = remoteDataSource.getAnimes(apiQuery, page, state.config.pageSize)

            val animes = apiResponse.data.documents
            val endOfPaginationReached = apiResponse.data.currentPage == apiResponse.data.lastPage

            localDataSource.database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    localDataSource.clearKeys()
                    localDataSource.clearAnime()
                }
                val prevKey = if(apiResponse.data.currentPage == ANI_STARTING_PAGE_INDEX) null else apiResponse.data.currentPage - 1
                val nextKey = if (endOfPaginationReached) null else apiResponse.data.currentPage + 1
                val keys = animes.map {
                    RemoteKeys(animeId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                localDataSource.insertKey(keys)
                localDataSource.insert(animes.toLocalModelList())
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}