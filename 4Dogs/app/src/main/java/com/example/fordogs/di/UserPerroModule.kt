package com.example.fordogs.di

import android.content.Context
import androidx.room.Room
import com.example.fordogs.data.local.UserPerroDb
import com.example.fordogs.data.local.dao.userPerroInfo.UserPerroDao
import com.example.fordogs.data.repository.Firestore.FirestoreApi
import com.example.fordogs.data.repository.Firestore.FirestoreRepository
import com.example.fordogs.data.repository.Firestore.FirestoreRepositoryImpl
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserPerroModule {

    @Provides
    @Singleton
    fun provideUserPerroInfoDb(
        @ApplicationContext context: Context
    ) : UserPerroDb {
        return Room.databaseBuilder(
            context,
            UserPerroDb::class.java,
            "userPerroDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserPerroDao(
        database: UserPerroDb
    ): UserPerroDao {
        return database.userPerroDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: UserPerroDao,
        api: FirestoreRepository
    ): UserPerroRepository {
        return UserPerroRepositoryImpl(
            userPerroDao = dao,
            firestoreRepository = api
        )
    }

}