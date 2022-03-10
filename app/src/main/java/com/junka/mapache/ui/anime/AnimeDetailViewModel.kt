package com.junka.mapache.ui.anime

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.junka.mapache.data.model.Document
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = AnimeDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val anime = args.anime

    class UiState(
        val loading: Boolean = false,
        val anime: Document
    )

    private val _state = MutableStateFlow(UiState(false, anime))
    val state: StateFlow<UiState> = _state.asStateFlow()
}