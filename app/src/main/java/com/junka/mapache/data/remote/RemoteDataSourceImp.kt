package com.junka.mapache.data.remote

import com.junka.mapache.data.remote.model.AniResult

class RemoteDataSourceImp(
    private val aniService: AniService
) : RemoteDataSource {

    override suspend fun getAnimes(): AniResult {
        return  aniService.getAnimes()
    }
}