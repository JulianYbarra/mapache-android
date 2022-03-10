package com.junka.mapache.data.remote

import com.junka.mapache.data.model.AniResult

interface RemoteDataSource {
    suspend fun getAnimes() : AniResult
}