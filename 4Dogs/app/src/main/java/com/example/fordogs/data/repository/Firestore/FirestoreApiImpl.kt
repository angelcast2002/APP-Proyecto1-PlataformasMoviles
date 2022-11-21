package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.dto.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirestoreApiImpl(private val db: FirebaseFirestore) : FirestoreApi {
    override suspend fun getOneUserPerroInfo(id: String): UserDto? {
        return try {
            val response = db.collection("users").document(id).get().await()
            val user = response?.toObject<UserDto>()
            return user


        } catch (e: Exception) {
            println(e)
            return UserDto()
        }

    }

    override suspend fun getUserPerroInfo(): Resource<UserPerro> {
        return try {
            val response = db.collection("users").document().get().await()
            val userPerro = response.toObject(UserPerro::class.java)
            if (userPerro != null)
                Resource.Success(data = userPerro)
            else
                Resource.Error(message = "User not found")
        } catch (e: Exception) {
            Resource.Error(message = "User not found")
        }
    }

    override suspend fun setUserPerroInfo(data: UserPerro): Resource<UserPerro> {

        // crear un nuevo documento con el id de
        return try {
            db.collection("users").document(data.id).set(data).await()
            Resource.Success(data = data)
        } catch (e: Exception) {
            Resource.Error(message = "User not created")
        }


    }



}
