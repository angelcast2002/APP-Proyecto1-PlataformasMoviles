package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.dto.UserDto

interface FirestoreApi {
    suspend fun getOneUserPerroInfo(id:String): UserDto?
    suspend fun getUserPerroInfo(): Resource<UserPerro>
    suspend fun setUserPerroInfo(data: UserPerro): Resource<UserPerro>
}