package com.junka.mapache.data.remote

import com.junka.mapache.data.remote.model.AniResult

class RemoteDataSourceImp(
    private val aniService: AniService
) : RemoteDataSource {

    override suspend fun getAnimes(): AniResult {
        return aniService.getAnimes()
    }

    override suspend fun getAnimes(query: String, page: Int, pageSize: Int): AniResult {
        return aniService.getAnimes(query,page,pageSize)
    }
}