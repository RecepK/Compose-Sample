package com.kurban.recep.data.api

import com.kurban.recep.common.Constants
import com.kurban.recep.common.Resource
import com.kurban.recep.data.model.entity.MediaType
import com.kurban.recep.data.model.response.DataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val api: Api) {

    fun getDataList(
        term: String,
        media: MediaType,
        limit: Int
    ): Flow<Resource<DataResponse>> {
        return invoke {
            api.getData(
                term = term.lowercase(),
                media = media.value.lowercase(),
                country = Constants.COUNTRY,
                limit = limit
            )
        }
    }

    private fun <T> invoke(request: suspend () -> Response<T>): Flow<Resource<T>> =
        flow<Resource<T>> {
            val response = request.invoke()

            when (response.isSuccessful) {
                false -> {
                    emit(Resource.Error(message = "Fail Response Exception"))
                }
                true -> {
                    response.body()?.let {
                        emit(Resource.Success(data = it))
                    } ?: run {
                        emit(Resource.Error(message = "Null Data Exception"))
                    }
                }
            }
        }.onStart {
            emit(Resource.Loading(true))
        }.catch {
            emit(Resource.Error(message = "Service Response Exception"))
        }.onCompletion {
            //emit(Resource.Loading)
        }.flowOn(Dispatchers.IO)
}