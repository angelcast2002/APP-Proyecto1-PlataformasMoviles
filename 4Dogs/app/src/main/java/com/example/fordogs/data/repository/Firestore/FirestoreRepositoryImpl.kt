package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.local.entity.UserPerro

class FirestoreRepositoryImpl(private val api: FirestoreApi) : FirestoreRepository {

    override suspend fun getUserPerroInfo(data: UserPerro) = api.getUserPerroInfo(data)
    override suspend fun setUserPerroInfo(data: UserPerro) = api.setUserPerroInfo(data)

}