package com.junka.mapache.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.useCase.AnimeListUseCase
import com.junka.mapache.domain.useCase.RefreshAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeListUseCase: AnimeListUseCase,
    private val refreshAnimeListUseCase: RefreshAnimeListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
        viewModelScope.launch {
            animeListUseCase().collect { animes ->
                _state.value = UiState(animes = animes)
            }
        }
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            refreshAnimeListUseCase()
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Anime>? = null
    )
}