package com.junka.mapache.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.paging.map
import com.junka.mapache.R
import com.junka.mapache.common.launchAndCollect
import com.junka.mapache.data.model.Anime
import com.junka.mapache.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var homeState: HomeState

    private val animeAdapter: AnimePagingAdapter by lazy {
        AnimePagingAdapter() { anime -> homeState.onAnimeClicked(anime.id) }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = buildHomeState()

        val binding = FragmentHomeBinding.bind(view).apply {
            recycler.adapter = animeAdapter
        }

        launchAndCollect(viewModel.state) { binding.updateUI(it) }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.map { it.animes }.collectLatest{
                    it?.let { animeAdapter.submitData(it)
                        Toast.makeText(requireContext(), animeAdapter.itemCount.toString(), Toast.LENGTH_SHORT).show()
                    }

                }}
            }
    }

    private fun FragmentHomeBinding.updateUI(state: HomeViewModel.UiState) {
        loading = state.loading
//        animes = state.animes
        error = state.error?.let(homeState::errorToString)
    }
}