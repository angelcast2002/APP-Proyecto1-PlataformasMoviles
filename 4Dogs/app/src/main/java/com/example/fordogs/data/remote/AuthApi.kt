package com.example.fordogs.data.remote

import com.example.fordogs.data.Resource

interface AuthApi {
    suspend fun signInWithEmailAndPasword(email: String, password: String): String?
    suspend fun signUpWithEmailAndPasword(email: String, password: String): String?
}