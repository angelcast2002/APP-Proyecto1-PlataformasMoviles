package com.example.fordogs.data.repository.Firebase

interface FirebaseRepository {
    suspend fun signInWithEmailAndPasword(email: String, password: String): String?
    suspend fun signUpWithEmailAndPasword(email: String, password: String): String?
}