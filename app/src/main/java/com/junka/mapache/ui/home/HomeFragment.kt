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

    private val viewModel: HomeViewModel  by viewModels()

    private val animeAdapter : AnimeAdapter by lazy { AnimeAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentHomeBinding.bind(view).apply {
            recycler.adapter = animeAdapter
        }
        viewLifecycleOwner.launchAndCollect(viewModel.state){ binding.updateUI(it)}
    }

    private fun FragmentHomeBinding.updateUI(state: HomeViewModel.UiState) {
        animeAdapter.submitList(state.animes)
    }
}