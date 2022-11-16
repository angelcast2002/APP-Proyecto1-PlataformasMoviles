package com.example.fordogs.data.repository.eventosRepo

import com.example.fordogs.data.local.entity.Event

interface EventRepository {

    fun getEvents() : List<Event>

    suspend fun getEventById(id: Int)

    suspend fun insertEvent(event: Event?)

    suspend fun deleteEvent(id: Int)

    suspend fun updateEvent(event: Event?)

}