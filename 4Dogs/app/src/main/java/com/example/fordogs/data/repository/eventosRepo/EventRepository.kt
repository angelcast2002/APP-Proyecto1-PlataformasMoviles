package com.example.fordogs.data.repository.eventosRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.Event

interface EventRepository {

    suspend fun getEvents() : Resource<List<Event>>

    suspend fun getEventById(id: Int) : Resource<Event>

    suspend fun insertEvent(event: Event?) : Resource<String>

    suspend fun deleteEvent(id: Int) : Resource<String>

    suspend fun updateEvent(event: Event?) : Resource<String>

}