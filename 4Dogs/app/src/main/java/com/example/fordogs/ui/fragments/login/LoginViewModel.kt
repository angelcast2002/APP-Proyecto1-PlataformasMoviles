package com.example.fordogs.ui.fragments.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.Firebase.FirebaseRepository
import com.example.fordogs.data.repository.Firestore.FirestoreUserPerroRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.ui.fragments.login.LogInConstants.Companion.ERROR
import com.example.fordogs.ui.util.dataStore
import com.example.fordogs.ui.util.getPreferencesValue
import com.example.fordogs.ui.util.savePreferencesValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: FirebaseRepository,
    private val repoFirestore: FirestoreUserPerroRepository,
    private val userdata: UserPerroRepository
    ): ViewModel() {

    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status

    sealed class Status{
        object Default: Status()
        object Loading: Status()
        class Error(val message: String): Status()
        object Succes: Status()
    }



    fun firebaseLogingIn(User: String, Password: String){
        viewModelScope.launch {
            _status.value = Status.Loading
            val userId = repo.signInWithEmailAndPasword(User, Password)
            if (userId != null){
                saveData(userId)

            }
            else{
                _status.value = Status.Error(ERROR)
            }
        }
    }

    fun saveData(data: String){
        CoroutineScope(Dispatchers.IO).launch{
            val dataUserPerro = repoFirestore.getOneUserPerroInfo(data)

            CoroutineScope(Dispatchers.Main).launch {
                saveDataToRoom(dataUserPerro)

            }
        }
        
    }
    fun saveDataToRoom(dataUserPerro: UserPerro){

        viewModelScope.launch {
            userdata.setUserPerroInfo(dataUserPerro)
            _status.value = Status.Succes
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