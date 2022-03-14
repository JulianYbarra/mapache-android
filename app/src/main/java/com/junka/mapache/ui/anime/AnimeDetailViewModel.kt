package com.junka.mapache.ui.anime

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junka.mapache.data.model.Anime
import com.junka.mapache.domain.useCase.GetAnimeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAnimeDetailUseCase: GetAnimeDetailUseCase,
) : ViewModel() {

    private val args = AnimeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    class UiState(
        val loading: Boolean = false,
        val anime: Anime? = null
    )

    init {
        viewModelScope.launch {
            getAnimeDetailUseCase(args.id).collect {
                _state.value = UiState(anime = it)
            }
        }
    }

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
}