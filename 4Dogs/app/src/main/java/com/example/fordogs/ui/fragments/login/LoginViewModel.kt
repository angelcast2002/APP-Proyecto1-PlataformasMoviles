package com.example.fordogs.ui.fragments.login

import android.app.Application
import android.content.Context
import androidx.datastore.dataStore
import android.content.Intent
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.repository.Firebase.FirebaseRepository
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.ERROR
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.PASSWORD
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.USER
import com.example.fordogs.ui.util.dataStore
import com.example.fordogs.ui.util.getPreferencesValue
import com.example.fordogs.ui.util.savePreferencesValue
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: FirebaseRepository
    ): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        class Error(val message: String): Status()
        class Succes(val userId: String): Status()
    }



    fun firebaseLogingIn(User: String, Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading
            val userId = repo.signInWithEmailAndPasword(User, Password)
            if (userId != null){
                _status.value = Status.Succes(userId)
            }
            else{
                _status.value = Status.Error(ERROR)
            }
        }
    }

    fun firebaseSignUp(User: String, Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading

            val userId = repo.signUpWithEmailAndPasword(User, Password)
            if (userId != null){
                _status.value = Status.Succes(userId)
            }
            else{
                _status.value = Status.Error(ERROR)
            }
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
        CoroutineScope(Dispatchers.IO).launch {
            val isLogged = context.dataStore.getPreferencesValue()
            if (isLogged == null){
                _isLogged.value = Logged.NotLogged
            } else {
                _isLogged.value = Logged.Succes
            }
        }
    }

    fun setDefaultLogged(){
        _isLogged.value = Logged.NotLogged
    }

    fun saveLog(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.savePreferencesValue()
            val isLogged = context.dataStore.getPreferencesValue()
            println(isLogged)
        }
    }
}