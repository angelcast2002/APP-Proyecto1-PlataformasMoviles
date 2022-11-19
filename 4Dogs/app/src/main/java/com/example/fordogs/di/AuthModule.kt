package com.example.fordogs.di

import com.example.fordogs.data.remote.AuthApi
import com.example.fordogs.data.repository.authFirebase.AuthRepository
import com.example.fordogs.data.repository.authFirebase.AuthRepositoryImplementation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthProvider() : FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideAuthApi(auth: FirebaseAuth) : AuthApi = AuthRepositoryImplementation(auth)

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi) : AuthRepository = AuthRepositoryImplementation(api)

    @Provides
    @Singleton
    fun provideApiProvider(): FirebaseFirestore = Firebase.firestore

}