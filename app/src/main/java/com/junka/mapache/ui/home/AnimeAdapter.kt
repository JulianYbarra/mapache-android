package com.junka.mapache.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.mapache.R
import com.junka.mapache.common.basicDiffUtil
import com.junka.mapache.common.inflate
import com.junka.mapache.domain.model.Anime
import com.junka.mapache.databinding.ViewAnimeItemBinding

class AnimeAdapter(
    val listener : (anime : Anime) -> Unit
) : ListAdapter<Anime, AnimeAdapter.ViewHolder>(
    basicDiffUtil { aOld, aNew -> aOld == aNew  }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_anime_item,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = ViewAnimeItemBinding.bind(view)

        fun bind(anime: Anime) = with(binding){
            this.anime = anime
        }
    }

}