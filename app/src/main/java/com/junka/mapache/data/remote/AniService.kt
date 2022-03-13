package com.junka.mapache.data.remote

import com.junka.mapache.data.remote.model.AniResult
import retrofit2.http.GET

interface AniService {
    @GET("/v1/anime")
    suspend fun getAnimes() : AniResult
}