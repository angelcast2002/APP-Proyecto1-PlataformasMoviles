package com.example.fordogs.data.remote.firebase

import com.example.fordogs.data.remote.AuthApi

class FirebaseAuthApiImplementation: AuthApi {
    override suspend fun signInWithEmailAndPasword(email: String, password: String): String? {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithEmailAndPasword(email: String, password: String): String? {
        TODO("Not yet implemented")
    }
}
