package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro

interface FirestoreApi {
    suspend fun getUserPerroInfo(): Resource<UserPerro>
    suspend fun setUserPerroInfo(data: UserPerro): Resource<UserPerro>
}