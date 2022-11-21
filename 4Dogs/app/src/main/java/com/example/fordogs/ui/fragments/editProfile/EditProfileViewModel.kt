package com.example.fordogs.ui.fragments.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.repository.Firestore.FirestoreUserPerroRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.ALTO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.COLOR
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.COMIDA_FAV
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.IMG_PERRO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.LARGO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.NOMBRE
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.PESO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.RAZA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val repository: UserPerroRepository ,
    private val repo: FirestoreUserPerroRepository
) : ViewModel() {


    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status



    sealed class Status{
        object Default: Status()
        class Editing(val data: UserPerro): Status()
        object Loading: Status()
        class Succes(val message: String): Status()
        class Error(val message: String): Status()
    }

    fun getData(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.getUserPerroInfo()){
                is Resource.Success -> {
                    _status.value = Status.Editing(perroInfoResult.data!!)
                }
                is Resource.Error -> {
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }
    }

    fun saveChanges(data: UserPerro){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.updateUserPerroInfo(data)){
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
    fun setEditing(){
        _status.value = Status.Editing(UserPerro(
            nombre = NOMBRE,
            raza = RAZA,
            peso = PESO,
            color = COLOR,
            alto = ALTO,
            largo = LARGO,
            comidaFav = COMIDA_FAV,
            imagen = IMG_PERRO)
        )
    }
}