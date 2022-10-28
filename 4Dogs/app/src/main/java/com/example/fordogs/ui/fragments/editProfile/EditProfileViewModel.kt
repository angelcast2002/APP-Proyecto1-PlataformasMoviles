package com.example.fordogs.ui.fragments.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.userPerro
import com.example.fordogs.data.repository.UserPerroRepository
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val repository: UserPerroRepository
) : ViewModel() {


    private val _status = MutableStateFlow<Status>(Status.Default)
    val status : StateFlow<Status> = _status


    sealed class Status{
        object Default: Status()
        class Editing(val data: userPerro): Status()
        object Loading: Status()
        class Succes(val message: String): Status()
        class Error(val message: String): Status()
    }

    fun getData(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.getUserPerroInfo()){
                is Resource.Succes -> {
                    _status.value = Status.Editing(perroInfoResult.data!!)
                }
                is Resource.Error -> {
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }
    }

    fun saveChanges(data: userPerro){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.setUserPerroInfo(data)){
                is Resource.Succes -> {
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
        getData()
    }

}