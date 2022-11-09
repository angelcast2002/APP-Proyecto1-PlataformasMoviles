package com.example.fordogs.data.local.dao.Events

import androidx.room.*
import com.example.fordogs.data.local.entity.Event
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAllEvents(): Flow<List<Event>>

    @Query("SELECT * FROM event WHERE date = :date")
    fun getEventsByDate(date: String): Flow<List<Event>>

    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEventById(id: Int): Event?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)
}


