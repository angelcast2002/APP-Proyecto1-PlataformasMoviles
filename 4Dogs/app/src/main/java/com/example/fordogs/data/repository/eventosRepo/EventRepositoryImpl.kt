package com.example.fordogs.data.repository.eventosRepo

import com.example.fordogs.data.local.dao.events.EventDao
import com.example.fordogs.data.local.entity.Event

class EventRepositoryImpl (
    private val eventDao: EventDao
    ) : EventRepository {

    override fun getEvents(): List<Event> {
        return eventDao.allEventsList
    }

    override suspend fun getEventById(id: Int) {
        return eventDao.selectDataFromAnId(id)
    }

    override suspend fun insertEvent(event: Event?) {
        return eventDao.insertDataIntoEventList(event)
    }

    override suspend fun deleteEvent(id: Int) {
        return eventDao.deleteEventFromId(id)
    }

    override suspend fun updateEvent(event: Event?) {
        return eventDao.updateEvent(event)
    }

}