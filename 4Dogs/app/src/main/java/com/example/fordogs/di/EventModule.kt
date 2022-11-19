package com.example.fordogs.di

import android.content.Context
import androidx.room.Room
import com.example.fordogs.data.local.EventDb
import com.example.fordogs.data.local.dao.events.EventDao
import com.example.fordogs.data.repository.eventosRepo.EventRepository
import com.example.fordogs.data.repository.eventosRepo.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EventModule {

    @Provides
    @Singleton
    fun provideEventDb(
        @ApplicationContext context: Context
    ) : EventDb {
        return Room.databaseBuilder(
            context,
            EventDb::class.java,
            "eventDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventDao(
        database: EventDb
    ): EventDao {
        return database.eventDao()
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: EventDao
    ) : EventRepository {
        return EventRepositoryImpl(
            eventDao = dao
        )
    }
}