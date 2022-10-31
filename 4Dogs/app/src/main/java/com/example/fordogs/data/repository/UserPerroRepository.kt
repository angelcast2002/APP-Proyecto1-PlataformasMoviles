package com.example.fordogs.data.repository

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.userPerro

interface UserPerroRepository {

    suspend fun getUserPerroInfo(): Resource<userPerro>
    suspend fun setUserPerroInfo(data: userPerro): Resource<String>
    suspend fun updateUserPerroInfo(data: userPerro): Resource<String>
}