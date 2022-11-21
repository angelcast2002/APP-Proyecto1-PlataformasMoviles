package com.example.fordogs.data.local.dao.events

import androidx.room.*
import com.example.fordogs.data.local.entity.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    suspend fun getEvents(): List<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataIntoEventList(event: Event?)

    @Delete
    suspend fun deleteEventFromId(event: Event)

    @Query("SELECT * FROM event WHERE eventId = :id")
    suspend fun selectDataFromAnId(id: Int) : Event

    @Update
    suspend fun updateEvent(event: Event?)

    @Query("DELETE FROM event")
    suspend fun deleteAllEvents()

}