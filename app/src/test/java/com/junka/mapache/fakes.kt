package com.junka.mapache

import com.junka.mapache.data.DataSource
import com.junka.mapache.data.local.AnimeDao
import com.junka.mapache.data.local.LocalDataSourceImp
import com.junka.mapache.data.remote.AniService
import com.junka.mapache.data.remote.RemoteDataSourceImp
import com.junka.mapache.data.remote.model.AniResult
import com.junka.mapache.data.remote.model.Data
import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.data.remote.model.Document as RemoteAnime
import com.junka.mapache.data.local.model.Anime  as DatabaseAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow



class FakeAnimeDao(animes: List<DatabaseAnime> = emptyList()) : AnimeDao {

    private val inMemoryMovies = MutableStateFlow(animes)
    private lateinit var findMovieFlow: MutableStateFlow<DatabaseAnime>

    override fun getAll(): Flow<List<DatabaseAnime>> = inMemoryMovies

    override fun findById(id: Int): Flow<DatabaseAnime> {
        findMovieFlow = MutableStateFlow(inMemoryMovies.value.first { it.id == id })
        return findMovieFlow
    }

    override suspend fun insert(anime: List<DatabaseAnime>) {
        inMemoryMovies.value = anime

        if (::findMovieFlow.isInitialized) {
            anime.firstOrNull() { it.id == findMovieFlow.value.id }
                ?.let { findMovieFlow.value = it }
        }
    }

    override suspend fun update(anime: DatabaseAnime) {
        // empty
    }


    override suspend fun count(): Int = inMemoryMovies.value.size

}

class FakeRemoteService(private val animes: List<RemoteAnime> = emptyList()) : AniService {

    override suspend fun getAnimes() = AniResult(
        Data(
            1,
            1,
            animes,
            2
        ),
        "",
        200,
        ""
    )

}