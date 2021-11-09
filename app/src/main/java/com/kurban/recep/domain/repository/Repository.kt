package com.kurban.recep.domain.repository

import com.kurban.recep.common.Resource
import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.response.DataResponse
import kotlinx.coroutines.flow.Flow


interface Repository {

    fun getDataList(term: String, media: MediaType, limit: Int): Flow<Resource<DataResponse>>
}