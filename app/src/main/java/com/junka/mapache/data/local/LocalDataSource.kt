package com.junka.mapache.data.local

import com.junka.mapache.data.local.model.Anime
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun isEmpty() : Boolean
    fun getAnime () : Flow<List<Anime>>
    fun getAnimeById(id : Int) : Flow<Anime>
    fun insert(animes : List<Anime>)
    fun update(anime : Anime)
}