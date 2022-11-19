package com.example.fordogs.data.repository.authFirebase

import com.example.fordogs.data.Resource

interface AuthRepository {
    suspend fun signInWithEmailAndPasword(email: String, password: String): String?
    suspend fun signUpWithEmailAndPasword(email: String, password: String): String?
}