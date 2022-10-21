package com.example.fordogs.ui.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.ui.util.LogInConstants.Companion.ERROR
import com.example.fordogs.ui.util.LogInConstants.Companion.PASSWORD
import com.example.fordogs.ui.util.LogInConstants.Companion.USER
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        object Succes: Status()
        class Error(val message: String): Status()
    }

    fun loading(User: String,Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading
            delay(2000L)
            logIn(User, Password)
        }
    }

     private fun logIn(User: String, Password: String) {
        if (User == USER && Password == PASSWORD) {
            _status.value = Status.Succes
        }

        else {
            _status.value = Status.Error(ERROR)
        }
    }

    fun setDefault(){
        _status.value = Status.Default
    }
}