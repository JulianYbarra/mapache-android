package com.junka.mapache.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.junka.mapache.data.local.model.Anime
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM Anime ORDER BY score DESC")
    fun getAll() : Flow<List<Anime>>

    @Query("SELECT * FROM Anime WHERE id = :id ")
    fun findById(id : Int) : Flow<Anime>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime : List<Anime>)

    @Update
    suspend fun update(anime : Anime)

    @Query("SELECT COUNT(id) FROM Anime")
    suspend fun count() : Int

    @Query("SELECT * FROM Anime WHERE title LIKE :title ORDER BY score DESC")
    fun animeByTitle(title : String) : PagingSource<Int, Anime>

    @Query("DElETE from Anime")
    suspend fun clear()
}