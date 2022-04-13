package com.junka.mapache.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.mapache.domain.model.Anime

@BindingAdapter("items")
fun RecyclerView.setItems(anime: List<Anime>?) {
    if (anime != null) {
        (adapter as? AnimeAdapter)?.submitList(anime)
    }
}