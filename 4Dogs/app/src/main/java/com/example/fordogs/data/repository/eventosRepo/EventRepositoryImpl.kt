package com.example.fordogs.data.repository.eventosRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.events.EventDao
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_COMPLETE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_CREATE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_DELETE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_GET_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_GET_EVENTS
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_GET_EVENT_BY_ID
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.ERROR_UPDATE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.NO_EVENTS
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.SUCCESS_COMPLETE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.SUCCESS_CREATE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.SUCCESS_DELETE_EVENT
import com.example.fordogs.data.repository.eventosRepo.EventRepoConstants.Companion.SUCCESS_UPDATE_EVENT

class EventRepositoryImpl (
    private val eventDao: EventDao
    ) : EventRepository {

    override suspend fun getEvents(): Resource<List<Event>> {
        return try {
            val localEvents = eventDao.getEvents()
            if (localEvents.isEmpty()) {
                return Resource.Error(message = NO_EVENTS)
            } else (
                Resource.Success(localEvents)
            )

        } catch (ex: Exception){
            Resource.Error(message = ERROR_GET_EVENTS)
        }
    }

    override suspend fun getEventById(id: Int) : Resource<Event> {
        return try {
            val localEvent = eventDao.selectDataFromAnId(id)
            if (localEvent.eventId == 0) {
                return Resource.Error(message = ERROR_GET_EVENT)
            } else (
                Resource.Success(localEvent)
            )

        } catch (ex: Exception){
            Resource.Error(message = ERROR_GET_EVENT_BY_ID)
        }
    }

    override suspend fun insertEvent(event: Event?) : Resource<String> {
        return try {
            eventDao.insertDataIntoEventList(event)
            Resource.Success(data = SUCCESS_CREATE_EVENT)
        } catch (ex: Exception){
            Resource.Error(message = ERROR_CREATE_EVENT)
        }
    }

    override suspend fun deleteEvent(id: Int) : Resource<String> {
        return try {
            val localEvent = eventDao.selectDataFromAnId(id)
            if (localEvent.eventId == 0) {
                return Resource.Error(message = ERROR_GET_EVENT_BY_ID)
            } else {
                eventDao.deleteEventFromId(localEvent)
                Resource.Success(data = SUCCESS_DELETE_EVENT)
            }
        } catch (ex: Exception){
            Resource.Error(message = ERROR_DELETE_EVENT)
        }
    }

    override suspend fun updateEvent(event: Event?) : Resource<String> {
        return try {
            val localEvent = eventDao.selectDataFromAnId(event!!.eventId)
            if (localEvent.eventId == 0) {
                return Resource.Error(message = ERROR_GET_EVENT_BY_ID)
            } else {
                eventDao.updateEvent(event)
                Resource.Success(data = SUCCESS_UPDATE_EVENT)
            }
        } catch (ex: Exception){
            Resource.Error(message = ERROR_UPDATE_EVENT)
        }
    }

    override suspend fun completeEvent(id: Int): Resource<String> {
        return try {
            val localEvent = eventDao.selectDataFromAnId(id)
            if (localEvent.eventId == 0) {
                return Resource.Error(message = ERROR_COMPLETE_EVENT)
            } else {
                eventDao.deleteEventFromId(localEvent)
                Resource.Success(data = SUCCESS_COMPLETE_EVENT)
            }
        } catch (ex: Exception){
            Resource.Error(message = ERROR_COMPLETE_EVENT)
        }
    }

    override suspend fun deleteAllEvents(): Resource<String> {
        return try {
            eventDao.deleteAllEvents()
            Resource.Success(data = SUCCESS_DELETE_EVENT)
        } catch (ex: Exception){
            Resource.Error(message = ERROR_DELETE_EVENT)
        }
    }

}