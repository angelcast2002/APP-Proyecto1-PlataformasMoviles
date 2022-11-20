package com.example.fordogs.di

import com.example.fordogs.data.remote.FirebaseApi
import com.example.fordogs.data.remote.firebase.FirebaseApiImpl
import com.example.fordogs.data.repository.Firebase.FirebaseRepository
import com.example.fordogs.data.repository.Firebase.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideAuthApi(auth: FirebaseAuth) : FirebaseApi = FirebaseApiImpl(auth)

    @Provides
    @Singleton
    fun provideAuthRepository(api: FirebaseApi) : FirebaseRepository = FirebaseRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApiProvider(): FirebaseFirestore = Firebase.firestore

    /*@Provides
    @Singleton
    fun providePlaceApi(db: FirebaseFirestore): PlaceApi = FirestorePlaceApiImpl(db)

    @Provides
    @Singleton
    fun providePlaceRepository(api: PlaceApi) : PlaceRepository = PlaceRepositoryImpl(api)

     */

}