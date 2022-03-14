package com.junka.mapache.data.local

import com.junka.mapache.data.local.model.Anime
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun isEmpty() : Boolean
    fun getAnime () : Flow<List<Anime>>
    fun getAnimeById(id : Int) : Flow<Anime>
    suspend fun insert(animes : List<Anime>)
    suspend fun update(anime : Anime)
}