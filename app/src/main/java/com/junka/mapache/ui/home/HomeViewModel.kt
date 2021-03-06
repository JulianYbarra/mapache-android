package com.junka.mapache.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.mapache.common.Error
import com.junka.mapache.common.toError
import com.junka.mapache.domain.model.Anime
import com.junka.mapache.domain.useCase.AnimeListUseCase
import com.junka.mapache.domain.useCase.RefreshAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        viewModelScope.launch {
            animeListUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { animes -> _state.update { UiState(animes = animes) } }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { _state.value.copy(loading = true) }
            val error = refreshAnimeListUseCase()
            _state.update { it.copy(error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val animes: List<Anime>? = null,
        val error: Error? = null
    )
}