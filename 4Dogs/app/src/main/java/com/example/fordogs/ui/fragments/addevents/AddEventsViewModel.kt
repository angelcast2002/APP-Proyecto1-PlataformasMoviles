package com.example.fordogs.ui.fragments.addevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.fordogs.data.repository.eventosRepo.EventRepository
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddEventsViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        class Succes(val message: String): Status()
        class Error(val message: String): Status()
    }

    fun getEvents(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when (val result = repository.getEvents()){
                is Resource.Succes -> {
                    _status.value = Status.Succes(result.data!!.toString())
                }
                is Resource.Error -> {
                    _status.value = Status.Error(result.message!!)
                }
            }

        }
    }

    fun setDefault(){
        _status.value = AddEventsViewModel.Status.Default
    }

}