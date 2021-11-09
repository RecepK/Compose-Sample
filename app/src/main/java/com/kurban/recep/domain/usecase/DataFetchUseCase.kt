package com.kurban.recep.domain.usecase

import com.kurban.recep.common.Resource
import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.response.DataResponse
import com.kurban.recep.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DataFetchUseCase @Inject constructor(
    private val repository: Repository
) {

    fun getDataList(term: String, media: MediaType, limit: Int): Flow<Resource<DataResponse>> {
        return repository.getDataList(term = term, media = media, limit = limit)
    }
}