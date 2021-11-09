package com.kurban.recep.data.api

import com.kurban.recep.data.model.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("search")
    suspend fun getData(
        @Query("term") term: String,
        @Query("media") media: String,
        @Query("country") country: String,
        @Query("limit") limit: Int
    ): Response<DataResponse>
}