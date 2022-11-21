package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.dto.mapToEntity

class FirestoreUserPerroRepositoryImpl(private val api: FirestoreApi) :
    FirestoreUserPerroRepository {
    override suspend fun getOneUserPerroInfo(id: String): UserPerro {

        return try {
            val userDto = api.getOneUserPerroInfo(id)
            userDto?.mapToEntity() ?: UserPerro()
        } catch (e: Exception) {
            UserPerro()
        }

    }
    override suspend fun getUserPerroInfo() = api.getUserPerroInfo()
    override suspend fun setUserPerroInfo(data: UserPerro) = api.setUserPerroInfo(data)

}