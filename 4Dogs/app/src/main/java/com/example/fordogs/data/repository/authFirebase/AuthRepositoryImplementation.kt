package com.example.fordogs.data.repository.authFirebase

import com.example.fordogs.data.Resource
import com.example.fordogs.data.remote.AuthApi

class AuthRepositoryImplementation(
    private val api: AuthApi,
) : AuthRepository {
    override suspend fun signInWithEmailAndPasword(email: String, password: String): Resource<String?> {
        val authResponse = api.signInWithEmailAndPasword(email, password)

        return try {
            if (authResponse == null) {
                Resource.Error(message = "Error inesperado")
            } else {
                Resource.Success(data = authResponse)
            }
        } catch (e: Exception) {
            Resource.Error(message = "Error inesperado")
        }
    }

}
