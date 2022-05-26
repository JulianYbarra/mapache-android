package com.junka.mapache.ui.home

import app.cash.turbine.test
import com.junka.mapache.CoroutinesTestRule
import com.junka.mapache.domain.model.Anime
import com.junka.mapache.domain.useCase.AnimeListUseCase
import com.junka.mapache.domain.useCase.RefreshAnimeListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var animeListUseCase: AnimeListUseCase

    @Mock
    lateinit var refreshAnimeListUseCase: RefreshAnimeListUseCase

    private lateinit var vm: HomeViewModel

    private var animes =
        listOf(
            Anime(
                0,
                "",
                "",
                "description",
                "title",
                "",
                0,
                5,
                1,
                false,
                10,
                3,
                3,
                "",
                1,
                "",
                1
            )
        )



    @Before
    fun setUp() {
        whenever(animeListUseCase()).thenReturn(flowOf(animes))
        vm = HomeViewModel(animeListUseCase, refreshAnimeListUseCase)
    }


    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.state.test {
            assertEquals(HomeViewModel.UiState(), awaitItem())
            assertEquals(HomeViewModel.UiState(animes = animes), awaitItem())
            cancel()
        }
    }

    @Test
    fun `anime are requested when UI screen starts`() = runTest {
        vm.refresh()
        runCurrent()

        verify(refreshAnimeListUseCase).invoke()
    }
}