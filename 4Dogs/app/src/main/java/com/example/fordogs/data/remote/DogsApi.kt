package com.example.fordogs.data.remote

import com.example.fordogs.data.remote.dto.PerroTipsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DogsApi {

    @Headers("X-Api-Key: IFkffJABy+V9Az2kJd+7Xw==JLkMqvK1wgJOtY7l")
    @GET("/v1/dogs")
    suspend fun getDogsTips(
        @Query("name") name: String
    ): PerroTipsDto
}