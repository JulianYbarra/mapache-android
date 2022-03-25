package com.junka.mapache.ui.home

import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.mapache.data.model.Anime

@BindingAdapter("items")
fun RecyclerView.setItems(anime: PagingData<Anime>?) {
//    if (anime != null) {
//        (adapter as? AnimePagingAdapter)?.submitData(anime)
//    }
}