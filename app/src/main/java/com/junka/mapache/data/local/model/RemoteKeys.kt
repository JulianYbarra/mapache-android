package com.junka.mapache.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RemoteKeys(
    @PrimaryKey val animeId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)