package com.junka.mapache.data.remote

import com.junka.mapache.data.remote.model.AniResult
import retrofit2.http.GET
import retrofit2.http.Query

interface AniService {
    @GET("/v1/anime")
    suspend fun getAnimes(): AniResult


    @GET("/v1/anime")
    suspend fun getAnimes(
        @Query("title") title: String,
        @Query("page") page : Int,
        @Query("per_page") perPage : Int
    ): AniResult

}