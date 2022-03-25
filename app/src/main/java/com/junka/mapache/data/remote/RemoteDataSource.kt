package com.junka.mapache.data.remote

import com.junka.mapache.data.remote.model.AniResult

interface RemoteDataSource {
    suspend fun getAnimes() : AniResult
    suspend fun getAnimes(query : String, page : Int,pageSize : Int) : AniResult
}