package com.junka.mapache.data.local

import com.junka.mapache.data.local.model.Anime
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImp(
    private val dao: AnimeDao) : LocalDataSource {

    override fun isEmpty(): Boolean = dao.count() == 0
    override fun getAnime(): Flow<List<Anime>> = dao.getAll()
    override fun getAnimeById(id: Int): Flow<Anime> = dao.findById(id)
    override fun insert(animes: List<Anime>) = dao.insert(animes)
    override fun update(anime: Anime) = dao.update(anime)
}