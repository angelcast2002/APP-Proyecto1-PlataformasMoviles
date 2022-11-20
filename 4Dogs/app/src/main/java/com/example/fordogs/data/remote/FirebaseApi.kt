package com.example.fordogs.data.remote

import com.example.fordogs.data.Resource


interface FirebaseApi {
    suspend fun signInWithEmailAndPasword(email: String, password: String): Resource<String>
    suspend fun signUpWithEmailAndPasword(email: String, password: String): Resource <String>
}