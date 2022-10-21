package com.example.fordogs.ui.fragments.viewModelActivity

import androidx.lifecycle.ViewModel
import com.example.fordogs.ui.util.RegisterConstants.Companion.DEFAULT_USER_NAME
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RegisterToLoginViewModel:  ViewModel() {
    private val _name = MutableStateFlow<String>(DEFAULT_USER_NAME)
    val name: StateFlow<String> = _name

    fun nameUser(Name: String){
        _name.value = Name
    }
}