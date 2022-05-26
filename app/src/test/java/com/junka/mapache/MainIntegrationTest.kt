package com.junka.mapache

import app.cash.turbine.test
import com.junka.mapache.data.DataSource
import com.junka.mapache.data.local.LocalDataSourceImp
import com.junka.mapache.data.remote.RemoteDataSourceImp
import com.junka.mapache.domain.model.Anime
import com.junka.mapache.domain.repository.AnimeRepository
import com.junka.mapache.domain.useCase.AnimeListUseCase
import com.junka.mapache.domain.useCase.RefreshAnimeListUseCase
import com.junka.mapache.ui.home.HomeViewModel
import com.junka.mapache.ui.home.HomeViewModel.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

import com.junka.mapache.data.remote.model.Document as RemoteAnime
import com.junka.mapache.data.local.model.Anime  as DatabaseAnime


@ExperimentalCoroutinesApi
class MainIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()


    @Test
    fun `data is loaded from server when local source is empty`() = runTest {
        val remoteData = buildRemoteAnime(4, 5, 6)
        val vm = buildViewModelWith(
            localData = emptyList(),
            remoteData = remoteData
        )

        vm.refresh()

        vm.state.test {
            assertEquals(UiState(), awaitItem())
            assertEquals(UiState(animes = emptyList(), loading = false), awaitItem())
            assertEquals(UiState(animes = emptyList(), loading = true), awaitItem())

            val movies = awaitItem().animes!!
            assertEquals("Title 4", movies[0].title)
            assertEquals("Title 5", movies[1].title)
            assertEquals("Title 6", movies[2].title)

            cancel()
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = buildDatabaseAnime(1, 2, 3)
        val remoteData = buildRemoteAnime(4, 5, 6)
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData
        )

        vm.state.test {
            assertEquals(UiState(), awaitItem())

            val movies = awaitItem().animes!!
            assertEquals("Title 1", movies[0].title)
            assertEquals("Title 2", movies[1].title)
            assertEquals("Title 3", movies[2].title)

            cancel()
        }
    }

    private fun buildViewModelWith(
        localData: List<DatabaseAnime> = emptyList(),
        remoteData: List<RemoteAnime> = emptyList()
    ): HomeViewModel {
        val animeRepository = buildRepositoryWith(localData, remoteData)
        val animeListUseCase = AnimeListUseCase(animeRepository)
        val refreshAnimeListUseCase = RefreshAnimeListUseCase(animeRepository)
        return HomeViewModel(animeListUseCase, refreshAnimeListUseCase)
    }
}