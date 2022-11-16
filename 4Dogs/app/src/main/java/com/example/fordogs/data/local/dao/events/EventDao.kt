package com.example.fordogs.data.local.dao.events

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fordogs.data.local.entity.Event

@Dao
interface EventDao {

    @get:Query("SELECT * FROM event")
    val allEventsList: List<Event>

    @Insert
    fun insertDataIntoEventList(event: Event?)

    @Query("DELETE FROM event WHERE eventId = :id")
    fun deleteEventFromId(id: Int)

    @Query("SELECT * FROM event WHERE eventId = :id")
    fun selectDataFromAnId(id: Int)

    @Update
    fun updateEvent(event: Event?)

}