package com.example.fordogs.ui.fragments.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.remote.dto.PerroTipsRecyclerView
import com.example.fordogs.data.repository.perroTipsRepo.PerroTipsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsFragmentViewModel @Inject constructor(
    private val tips: PerroTipsRepository
): ViewModel() {

    private val _statusTips = MutableStateFlow<StatusTips>(StatusTips.Default)
    val statusTips: StateFlow<StatusTips> = _statusTips

    sealed class StatusTips{
        object Default: StatusTips()
        class Error(val message: String): StatusTips()
        class Succes(val data: PerroTips): StatusTips()
    }

    fun prueba(name:String){
        viewModelScope.launch {

            when(val resultado = tips.getPerroTips(name)) {
                is Resource.Error -> {
                    _statusTips.value = StatusTips.Error(resultado.message!!)
                }
                is Resource.Success -> {
                    _statusTips.value = StatusTips.Succes(resultado.data!!)
                }
            }
        }
    }

    fun pruebaToDefault(){
        _statusTips.value = StatusTips.Default
    }
}