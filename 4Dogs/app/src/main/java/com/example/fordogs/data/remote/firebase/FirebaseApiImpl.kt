package com.example.fordogs.data.remote.firebase

import com.example.fordogs.data.Resource
import com.example.fordogs.data.remote.FirebaseApi
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseApiImpl: FirebaseApi {
    override suspend fun signInWithEmailAndPasword(email: String, password: String): Resource<String> {

        val auth = Firebase.auth


        auth.signInWithEmailAndPassword(email, password).await()
        val userInfo = response.user

        return try {
            if (userInfo != null) {
                Resource.Success(data = userInfo.uid)
            } else {
                Resource.Error(message = "User not found")
            }
        } catch (e: Exception) {
            Resource.Error(message = "User not found")
        }

    }

    override suspend fun signUpWithEmailAndPasword(email: String, password: String): Resource<String> {
        val auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password).await()
        val userInfo = response.user

        return try {
            if (userInfo != null) {
                Resource.Success(data = userInfo.uid)
            } else {
                Resource.Error(message = "User not created")
            }
        } catch (e: Exception) {
            Resource.Error(message = "User not Created")
        }
    }
}
