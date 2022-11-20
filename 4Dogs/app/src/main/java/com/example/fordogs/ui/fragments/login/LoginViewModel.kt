package com.example.fordogs.ui.fragments.login

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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(private val repo: FirebaseRepository): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        object Succes: Status()
        class Error(val message: String): Status()
        class UserId(val userId: String): Status()
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



    fun firebaseLogingIn(User: String, Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading
            delay(2000L)

            val userId = repo.signInWithEmailAndPasword(User, Password)
            if (userId != null){
                _status.value = Status.UserId(userId)
            }
            else{
                _status.value = Status.Error(ERROR)
            }
        }
    }

    fun firebaseSignUp(User: String, Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading
            delay(2000L)

            val userId = repo.signUpWithEmailAndPasword(User, Password)
            if (userId != null){
                _status.value = Status.UserId(userId)
            }
            else{
                _status.value = Status.Error(ERROR)
            }
        }
    }


    fun setDefault(){
        _status.value = Status.Default
    }
}