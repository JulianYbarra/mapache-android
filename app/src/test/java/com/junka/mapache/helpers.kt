package com.junka.mapache


import com.junka.mapache.data.DataSource
import com.junka.mapache.data.local.LocalDataSourceImp
import com.junka.mapache.data.remote.RemoteDataSourceImp
import com.junka.mapache.data.remote.model.Descriptions
import com.junka.mapache.data.remote.model.Titles
import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.data.remote.model.Document as RemoteAnime
import com.junka.mapache.data.local.model.Anime  as DatabaseAnime

fun buildRepositoryWith(
    localData : List<DatabaseAnime> = emptyList(),
    remoteData : List<RemoteAnime> = emptyList()
) : AnimeRepository {

    val remoteService = FakeRemoteService(remoteData)

    val localDataSource = LocalDataSourceImp(FakeAnimeDao(localData))
    val remoteDataSource = RemoteDataSourceImp(remoteService)

    val dataSource = DataSource(localDataSource,remoteDataSource)

    return AnimeRepository(dataSource)
}

fun buildDatabaseAnime(vararg id: Int) = id.map {
    DatabaseAnime(
        id = it,
        title = "Title $it",
        bannerImage = "Banner $it",
        coverImage = "Cover $it",
        descriptions = "Description $it",
        endDate = "",
        episodeDuration = 1,
        episodesCount = 5,
        format = 2,
        nsfw = false,
        score = 5,
        seasonPeriod = 1,
        seasonYear = 3,
        startDate = "",
        status = 1,
        trailerUrl = "Trailer $it",
        weeklyAiringDay = 3
    )
}

fun buildRemoteAnime(vararg id: Int) = id.map {
    RemoteAnime(
        tmdbId = it,
        weeklyAiringDay = 2,
        trailerUrl = "Trailer $it",
        status = 1,
        startDate = "",
        seasonYear = 2,
        nsfw = false,
        score = 5,
        seasonPeriod = 3,
        format = 2,
        episodesCount = 5,
        episodeDuration = 20,
        endDate = "",
        descriptions = Descriptions("Description $it",null,null,null),
        coverImage = "Cover $it",
        bannerImage = "Banner $it",
        anilistId = 1,
        coverColor = "#444444",
        genres = null,
        hasCoverImage = true,
        id = it,
        malId = 1,
        prequel = 1,
        sagas = null,
        titles = Titles("Title $it",null,null,null)
    )
}