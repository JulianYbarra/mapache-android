package com.junka.mapache.data.local

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
}