package com.example.fordogs.ui.fragments.editProfile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EditProfileViewModel: ViewModel() {
    private val _raza = MutableStateFlow<String>("Chihuahua")
    val raza : StateFlow<String> = _raza

    private val _peso = MutableStateFlow<String>("10")
    val peso : StateFlow<String> = _peso

    private val _color = MutableStateFlow<String>("Cafe")
    val color : StateFlow<String> = _color

    private val _alto = MutableStateFlow<String>("10")
    val alto : StateFlow<String> = _alto

    private val _largo = MutableStateFlow<String>("25")
    val largo : StateFlow<String> = _largo

    private val _comiddaFav = MutableStateFlow<String>("Pizza")
    val comidaFav : StateFlow<String> = _comiddaFav

    private val _state = MutableStateFlow<Status>(Status.Editing)
    val state : StateFlow<Status> = _state

    sealed class Status{
        object Editing: Status()
    }

}