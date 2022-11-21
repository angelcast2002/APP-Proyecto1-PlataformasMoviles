package com.example.fordogs.data.repository.Firestore

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.dto.UserDto
import com.example.fordogs.data.repository.Firestore.FirestoreConstans.Companion.MENSAJE
import com.example.fordogs.data.repository.Firestore.FirestoreConstans.Companion.MENSAJE_USURIO_NO_CREADO
import com.example.fordogs.data.repository.Firestore.FirestoreConstans.Companion.USERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirestoreApiImpl(private val db: FirebaseFirestore) : FirestoreApi {
    override suspend fun getOneUserPerroInfo(id: String): UserDto? {
        return try {
            val response = db.collection(USERS).document(id).get().await()
            val user = response?.toObject<UserDto>()
            return user


        } catch (e: Exception) {
            println(e)
            return UserDto()
        }

    }

    override suspend fun getUserPerroInfo(): Resource<UserPerro> {
        return try {
            val response = db.collection(USERS).document().get().await()
            val userPerro = response.toObject(UserPerro::class.java)
            if (userPerro != null)
                Resource.Success(data = userPerro)
            else
                Resource.Error(message = MENSAJE)
        } catch (e: Exception) {
            Resource.Error(message = MENSAJE)
        }
    }

    override suspend fun setUserPerroInfo(data: UserPerro): Resource<UserPerro> {

        // crear un nuevo documento con el id de
        return try {
            db.collection(USERS).document(data.id).set(data).await()
            Resource.Success(data = data)
        } catch (e: Exception) {
            Resource.Error(message = MENSAJE_USURIO_NO_CREADO)
        }


    }



}
