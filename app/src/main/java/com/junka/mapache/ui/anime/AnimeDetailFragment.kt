package com.junka.mapache.ui.anime

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junka.mapache.R
import com.junka.mapache.common.launchAndCollect
import com.junka.mapache.databinding.FragmentAnimeDetailBinding

class AnimeDetailFragment : Fragment(R.layout.fragment_anime_detail) {

    private val viewModel: AnimeDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAnimeDetailBinding.bind(view).apply {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state){ binding.setUI(it) }
    }

    private fun FragmentAnimeDetailBinding.setUI(state : AnimeDetailViewModel.UiState) {
        loading = state.loading
        anime = state.anime
    }
}