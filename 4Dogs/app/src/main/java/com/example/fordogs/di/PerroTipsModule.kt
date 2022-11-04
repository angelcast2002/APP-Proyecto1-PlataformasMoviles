package com.example.fordogs.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fordogs.data.local.PerroTipsDb
import com.example.fordogs.data.local.dao.perroTips.PerroTipsDao
import com.example.fordogs.data.remote.DogsApi
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepository
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepsitoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PerroTipsModule {

    @Provides
    @Singleton
    fun providePerroTipsDb(
        @ApplicationContext context: Context
    ): PerroTipsDb {
        return Room.databaseBuilder(
            context,
            PerroTipsDb::class.java,
            "perroTipsDb"
        ).build()
    }

    @Provides
    @Singleton
    fun providesPerroTipsDao(
        database: PerroTipsDb
    ): PerroTipsDao {
        return database.perroTipsDao()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePerroTipsApi(
        client: OkHttpClient
    ): DogsApi {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DogsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: PerroTipsDao,
        api: DogsApi
    ): PerroTipsRepository {
        return PerroTipsRepsitoryImpl(
            perroTipsDao = dao,
            api = api
        )
    }


}
