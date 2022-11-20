package com.example.fordogs.ui.fragments.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.dto.PerroTipsRecyclerView
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepository
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsFragmentViewModel @Inject constructor(
    private val tips: PerroTipsRepository,
    private val repository: UserPerroRepository
): ViewModel() {

    private val _statusTips = MutableStateFlow<StatusTips>(StatusTips.Default)
    val statusTips: StateFlow<StatusTips> = _statusTips

    private val _status = MutableStateFlow<Status>(Status.Loading)
    val status : StateFlow<Status> = _status

    sealed class StatusTips{
        object Default: StatusTips()
        object Loading: StatusTips()
        class Error(val message: String): StatusTips()
        class Succes(val data: PerroTips): StatusTips()
    }

    sealed class Status{
        class Succes(val data: UserPerro): Status()
        object Loading: Status()
        class Error(val message: String): Status()
    }


    fun getTips(name:String){
        viewModelScope.launch {
            _statusTips.value = StatusTips.Loading
            when(val resultado = tips.getPerroTips(name)) {
                is Resource.Error -> {
                    _statusTips.value = StatusTips.Error(resultado.message!!)
                }
                is Resource.Success -> {
                    _statusTips.value = StatusTips.Succes(resultado.data!!)
                    saveData(resultado.data)
                }
            }
        }
    }

    private fun saveData(data:PerroTips){
        viewModelScope.launch {
            tips.savePerroTips(data)
        }
    }

    fun pruebaToDefault(){
        _statusTips.value = StatusTips.Default
    }

    fun getData(){
        viewModelScope.launch {
            _status.value = Status.Loading
            when(val perroInfoResult = repository.getUserPerroInfo()){
                is Resource.Success -> {
                    _status.value = Status.Succes(perroInfoResult.data!!)
                }
                is Resource.Error -> {
                    _status.value = Status.Error(perroInfoResult.message!!)
                }
            }
        }
    }

    fun setLoading(){
        _status.value = Status.Loading
    }
}