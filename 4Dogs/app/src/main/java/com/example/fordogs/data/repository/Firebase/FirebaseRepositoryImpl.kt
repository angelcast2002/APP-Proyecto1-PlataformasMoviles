package com.example.fordogs.data.repository.Firebase

import com.example.fordogs.data.Resource

class FirebaseRepositoryImpl(private val api: FirebaseApi) : FirebaseRepository {

    override suspend fun signInWithEmailAndPasword(email: String, password: String): String? {
        val authResponse = api.signInWithEmailAndPasword(email, password)
        return try {
            if (authResponse is Resource.Success) {
                authResponse.data!!
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }


    }

    override suspend fun signUpWithEmailAndPasword(email: String, password: String): String? {
        val authResponse = api.signUpWithEmailAndPasword(email, password)
        return try {
            if (authResponse is Resource.Success) {
                authResponse.data!!
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

}
