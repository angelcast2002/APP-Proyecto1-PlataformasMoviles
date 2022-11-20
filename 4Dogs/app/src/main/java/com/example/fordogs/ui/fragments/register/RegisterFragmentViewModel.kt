package com.example.fordogs.ui.fragments.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.ui.util.dataStore
import com.example.fordogs.ui.util.getPreferencesValue
import com.example.fordogs.ui.util.savePreferencesValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    fun saveLog(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.savePreferencesValue()
            val isLogged = context.dataStore.getPreferencesValue()
            println(isLogged)
        }
    }

    fun setDefault(){
        _status.value = Status.Default
    }
}