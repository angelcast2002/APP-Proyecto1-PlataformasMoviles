package com.example.fordogs.ui.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserPerroRepository
): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Loading)
    val status : StateFlow<Status> = _status


    sealed class Status{
        class Succes(val data: UserPerro): Status()
        object Loading: Status()
        class Error(val message: String): Status()
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

    fun setLoading(){
        _status.value = Status.Loading
    }
}