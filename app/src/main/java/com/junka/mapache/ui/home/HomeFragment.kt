package com.junka.mapache.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.junka.mapache.R
import com.junka.mapache.common.launchAndCollect
import com.junka.mapache.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var homeState: HomeState

    private val animeAdapter: AnimeAdapter by lazy {
        AnimeAdapter() { anime -> homeState.onAnimeClicked(anime.id) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = buildHomeState()

        val binding = FragmentHomeBinding.bind(view).apply {
            recycler.adapter = animeAdapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { binding.updateUI(it) }
    }

    private fun FragmentHomeBinding.updateUI(state: HomeViewModel.UiState) {
        loading = state.loading
        animes = state.animes
    }
}