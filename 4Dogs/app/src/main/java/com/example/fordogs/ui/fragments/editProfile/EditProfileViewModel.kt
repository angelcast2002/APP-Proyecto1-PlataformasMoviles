package com.example.fordogs.ui.fragments.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.ui.util.EditProfileConstants.Companion.ALTO
import com.example.fordogs.ui.util.EditProfileConstants.Companion.COLOR
import com.example.fordogs.ui.util.EditProfileConstants.Companion.COMIDA_FAV
import com.example.fordogs.ui.util.EditProfileConstants.Companion.CONDUCTA
import com.example.fordogs.ui.util.EditProfileConstants.Companion.IMG_PERRO
import com.example.fordogs.ui.util.EditProfileConstants.Companion.JUGUETE_FAV
import com.example.fordogs.ui.util.EditProfileConstants.Companion.LARGO
import com.example.fordogs.ui.util.EditProfileConstants.Companion.PESO
import com.example.fordogs.ui.util.EditProfileConstants.Companion.RAZA
import com.example.fordogs.ui.util.RegisterConstants
import com.example.fordogs.ui.util.RegisterConstants.Companion.DEFAULT_USER_NAME
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditProfileViewModel: ViewModel() {


    private val _status = MutableStateFlow<Status>(Status.Editing)
    val status : StateFlow<Status> = _status


    sealed class Status{
        object Editing: Status()
        object Loading: Status()
        object Succes: Status()
        class Error(val message: Exception): Status()
    }

    fun saveChanges(){
        viewModelScope.launch {
            _status.value = Status.Loading
            delay(1000L)
            checkData()
        }
    }

    private fun checkData(){
        try {
            //api

            _status.value = Status.Succes
        } catch (e: Exception) {
            _status.value = Status.Error(e)
        }
    }

    fun setDefault(){
        _status.value = Status.Editing
    }

    fun nameUser(Name: String){
        _nombre.value = Name
    }
}