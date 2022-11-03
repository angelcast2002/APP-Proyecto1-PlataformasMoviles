package com.example.fordogs.data.remote

import com.example.fordogs.data.remote.dto.PerroTipsDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {

    @GET("/v1/dogs?{name}")
    suspend fun getDogsTips(
        @Path("name") name: String
    ): ArrayList<PerroTipsDtoItem>
}