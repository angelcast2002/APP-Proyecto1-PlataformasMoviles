package com.example.fordogs.ui.fragments.login

import android.app.Application
import android.content.Context
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.ERROR
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.PASSWORD
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.USER
import com.example.fordogs.ui.util.dataStore
import com.example.fordogs.ui.util.getPreferencesValue
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val context = getApplication<>().applicationContext
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

    private val _isLogged = MutableStateFlow<Logged>(Logged.NotLogged)
    val isLogged : StateFlow<Logged> = _isLogged

    sealed class Logged{
        object Succes: Logged()
        object NotLogged: Logged()
    }

    fun checkIsLogged(context: Context){
        viewModelScope.launch{
            val isLogged = context.dataStore.getPreferencesValue()
            if (isLogged.isNullOrEmpty()){
                _isLogged.value = Logged.NotLogged
            } else {
                _isLogged.value = Logged.Succes
            }
        }
    }
}