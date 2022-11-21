package com.example.fordogs.data.repository.Firebase

import com.example.fordogs.data.Resource
import com.example.fordogs.data.repository.Firebase.FirebaseConstants.Companion.MENSAJE
import com.example.fordogs.data.repository.Firebase.FirebaseConstants.Companion.MENSAJE_USER_NO_CREADO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseApiImpl(private val api: FirebaseAuth): FirebaseApi {
    override suspend fun signInWithEmailAndPasword(email: String, password: String): Resource<String> {

        return try {
            val response = api.signInWithEmailAndPassword(email, password).await()

            val user = response.user
            if (user != null)
                Resource.Success(data = user.uid)
            else
                Resource.Error(message = MENSAJE)
        } catch (e: Exception) {
            Resource.Error(message = MENSAJE)
        }



    }

    override suspend fun signUpWithEmailAndPasword(email: String, password: String): Resource<String> {
        return try {
            val response = api.createUserWithEmailAndPassword(email, password).await()

            val user = response.user
            if (user != null)
                Resource.Success(data = user.uid)
            else
                Resource.Error(message = MENSAJE_USER_NO_CREADO)
        } catch (e: Exception) {
            Resource.Error(message = MENSAJE_USER_NO_CREADO)
        }

    }
}