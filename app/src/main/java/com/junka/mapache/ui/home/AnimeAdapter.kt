package com.junka.mapache.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.junka.mapache.R
import com.junka.mapache.common.basicDiffUtil
import com.junka.mapache.common.inflate
import com.junka.mapache.common.loadUrl
import com.junka.mapache.data.model.Document
import com.junka.mapache.databinding.ViewAnimeItemBinding

class AnimeAdapter : ListAdapter<Document, AnimeAdapter.ViewHolder>(
    basicDiffUtil { aOld, aNew -> aOld == aNew  }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_anime_item,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = ViewAnimeItemBinding.bind(view)

        fun bind(document: Document) = with(binding){
            cover.loadUrl(document.coverImage)
            title.text = document.titles.en
            description.text = document.descriptions.en
            episodes.text = "Episodes : ${document.episodesCount}"
            score.text = "Score : ${document.score}"
        }
    }

}