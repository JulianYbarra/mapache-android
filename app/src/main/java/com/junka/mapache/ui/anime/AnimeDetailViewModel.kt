package com.junka.mapache.ui.anime

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.mapache.common.Error
import com.junka.mapache.common.toError
import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.useCase.GetAnimeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
) : ViewModel() {

    private val args = AnimeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    data class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null,
        val error: Error? = null
    )

    init {
        viewModelScope.launch {
            getAnimeDetailUseCase(args.id)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { anime -> _state.update { UiState(anime = anime) } }
        }
    }

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
}