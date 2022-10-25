package com.example.fordogs.ui.fragments.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.ui.util.EditProfileConstants.Companion.ALTO
import com.example.fordogs.ui.util.EditProfileConstants.Companion.COLOR
import com.example.fordogs.ui.util.EditProfileConstants.Companion.COMIDA_FAV
import com.example.fordogs.ui.util.EditProfileConstants.Companion.IMG_PERRO
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
    private val _imagen = MutableStateFlow<String>(IMG_PERRO)
    val imagen : StateFlow<String> = _imagen

    val _nombre = MutableStateFlow<String>(DEFAULT_USER_NAME)
    val nombre : StateFlow<String> = _nombre

    private val _raza = MutableStateFlow<String>(RAZA)
    val raza : StateFlow<String> = _raza

    private val _peso = MutableStateFlow<Int>(PESO)
    val peso : StateFlow<Int> = _peso

    private val _color = MutableStateFlow<String>(COLOR)
    val color : StateFlow<String> = _color

    private val _alto = MutableStateFlow<Int>(ALTO)
    val alto : StateFlow<Int> = _alto

    private val _largo = MutableStateFlow<Int>(LARGO)
    val largo : StateFlow<Int> = _largo

    private val _comiddaFav = MutableStateFlow<String>(COMIDA_FAV)
    val comidaFav : StateFlow<String> = _comiddaFav

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