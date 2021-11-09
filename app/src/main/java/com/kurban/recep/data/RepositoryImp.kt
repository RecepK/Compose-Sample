package com.kurban.recep.data

import com.kurban.recep.common.Resource
import com.kurban.recep.data.api.RemoteDataSource
import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.response.DataResponse
import com.kurban.recep.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun getDataList(
        term: String,
        media: MediaType,
        limit: Int
    ): Flow<Resource<DataResponse>> {
        return remoteDataSource.getDataList(term = term, media = media, limit = limit)
    }
}