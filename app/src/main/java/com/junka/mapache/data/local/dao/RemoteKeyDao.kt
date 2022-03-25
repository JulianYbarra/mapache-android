package com.junka.mapache.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junka.mapache.data.local.model.RemoteKeys

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM RemoteKeys WHERE animeId = :animeId")
    suspend fun remoteKeysAnimeId(animeId: Int): RemoteKeys?

    @Query("DELETE FROM RemoteKeys")
    suspend fun clear()
}