package com.junka.mapache.data

import com.junka.mapache.data.model.Document
import com.junka.mapache.data.remote.RemoteDataSource

class DataSource(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getAnimes() : List<Document>{
        return remoteDataSource.getAnimes().data.documents
    }
}