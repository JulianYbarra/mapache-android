package com.junka.mapache.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.mapache.data.model.Document

@BindingAdapter("items")
fun RecyclerView.setItems(anime: List<Document>?) {
    if (anime != null) {
        (adapter as? AnimeAdapter)?.submitList(anime)
    }
}