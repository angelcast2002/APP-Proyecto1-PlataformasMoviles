package com.example.fordogs.ui.fragments.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.ui.fragments.login.LogInConstants
import com.example.fordogs.ui.fragments.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val repository: UserPerroRepository
): ViewModel(){
    private val _status = MutableStateFlow<Status>(Status.Default)
    val status: StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        class Succes(val message: String): Status()
        class Error(val message: String): Status()
    }

    fun saveChanges(data: UserPerro){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.setUserPerroInfo(data)){
                is Resource.Success -> {
                    _status.value = Status.Succes(perroInfoResult.data!!)
                }
                is Resource.Error ->{
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }

    }


    fun setDefault(){
        _status.value = Status.Default
    }
}