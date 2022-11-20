package com.example.fordogs.data.remote.firebase

import com.example.fordogs.data.Resource
import com.example.fordogs.data.remote.FirebaseApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseApiImpl(private val api: FirebaseAuth): FirebaseApi {
    override suspend fun signInWithEmailAndPasword(email: String, password: String): Resource<String> {

        return try {
            val response = api.signInWithEmailAndPassword(email, password).await()

            val user = response.user
            if (user != null)
                Resource.Success(data = user.uid)
            else
                Resource.Error(message = "User not found")
        } catch (e: Exception) {
            Resource.Error(message = "User not found")
        }



    }

    override suspend fun signUpWithEmailAndPasword(email: String, password: String): Resource<String> {
        return try {
            val response = api.createUserWithEmailAndPassword(email, password).await()

            val user = response.user
            if (user != null)
                Resource.Success(data = user.uid)
            else
                Resource.Error(message = "User not created")
        } catch (e: Exception) {
            Resource.Error(message = "User not created")
        }

    }
}