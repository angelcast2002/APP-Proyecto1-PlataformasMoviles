package com.example.fordogs.ui.fragments.addevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.fordogs.data.repository.eventosRepo.EventRepository
import com.example.fordogs.ui.MainActivity
import com.example.fordogs.ui.fragments.register.RegisterFragmentViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class EventsManagementViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    private val _status = MutableStateFlow<EventStatus>(EventStatus.Default)
    val eventStatus : StateFlow<EventStatus> = _status

    sealed class EventStatus{
        object Default: EventStatus()
        class Editing(val event: Event): EventStatus()
        class Success(val message: String): EventStatus()
        class Error(val message: String): EventStatus()
    }

    fun createEvent(event: Event) =
        viewModelScope.launch {
            when(val result = repository.insertEvent(event)){
                is Resource.Success -> {
                    _status.value = EventStatus.Success(result.data!!)
                }
                is Resource.Error -> {
                    _status.value = EventStatus.Error(result.message!!)
                }
            }
        }

    private fun editEvent(eventId: Int) =
        viewModelScope.launch {
            when(val result = repository.getEventById(eventId)) {
                is Resource.Success -> {
                    _status.value = EventStatus.Editing(result.data!!)
                }
                is Resource.Error -> {
                    _status.value = EventStatus.Error(result.message!!)
                }
            }
        }

    fun setEventId(eventId: Int, isEditing: Boolean) {
        if(!isEditing) {
            _status.value = EventStatus.Default
        } else {
            editEvent(eventId)
        }
    }

    fun setDefault(){
        _status.value = EventStatus.Default
    }

}