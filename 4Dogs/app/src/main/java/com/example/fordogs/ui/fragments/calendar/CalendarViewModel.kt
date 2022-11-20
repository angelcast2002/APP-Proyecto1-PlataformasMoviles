package com.example.fordogs.ui.fragments.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.eventosRepo.EventRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val repository: UserPerroRepository,
    private val eventRepository: EventRepository
): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Loading)
    val status : StateFlow<Status> = _status

    private val _eventStatus = MutableStateFlow<EventStatus>(EventStatus.Loading)
    val eventStatus : StateFlow<EventStatus> = _eventStatus

    sealed class Status{
        object Loading: Status()
        class Succes(val data: UserPerro): Status()
        class Error(val message: String): Status()
    }

    sealed class EventStatus {
        object Loading: EventStatus()
        class Success(val data: List<Event>): EventStatus()
        class Deleted(val message: String): EventStatus()
        class Error(val message: String): EventStatus()
    }

    fun getData(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.getUserPerroInfo()){
                is Resource.Success -> {
                    _status.value = Status.Succes(perroInfoResult.data!!)
                }
                is Resource.Error -> {
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }
    }

    fun getEvents() {
        viewModelScope.launch {
            _eventStatus.value = EventStatus.Loading
            when(val eventsResult = eventRepository.getEvents()){
                is Resource.Success -> {
                    _eventStatus.value = EventStatus.Success(eventsResult.data!!)
                }
                is Resource.Error -> {
                    _eventStatus.value = EventStatus.Error(eventsResult.message!!)
                }
            }
        }
    }

    fun deleteEvent(eventId: Int) =
        viewModelScope.launch {
            _eventStatus.value = EventStatus.Loading
            when(val eventsResult = eventRepository.deleteEvent(eventId)){
                is Resource.Success -> {
                    _eventStatus.value = EventStatus.Deleted(eventsResult.data!!)
                }
                is Resource.Error -> {
                    _eventStatus.value = EventStatus.Error(eventsResult.message!!)
                }
            }
        }

}