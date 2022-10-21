package com.example.fordogs.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.example.fordogs.databinding.EditProfileLayoutBinding
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.fragments.viewModelActivity.RegisterToLoginViewModel
import com.example.fordogs.ui.util.BaseFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment: BaseFragment<RegisterLayoutBinding>(){

    private val RegisterToLoginViewModel: RegisterToLoginViewModel by activityViewModels()
    private lateinit var name: String

    override fun getViewBinding() = RegisterLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        hideNavBar()
        setListeners()
        setNameEditText()
    }

    private fun setNameEditText() {
        binding.textInputUserTextRegisterLayoutEditText.setText(name)
    }

    private fun setObservables() {
        lifecycleScope.launch{
            RegisterToLoginViewModel.name.collectLatest {
                name = RegisterToLoginViewModel.name.value
            }
        }
    }

    private fun setListeners() {
        binding.btRegresar.setOnClickListener{
            requireActivity().onBackPressed()
        }

        binding.btRegistraseRegisterLayout.setOnClickListener{
            name = binding.textInputUserTextRegisterLayoutEditText.text.toString()
            RegisterToLoginViewModel.nameUser(name)
            requireView().findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToEditProfileFragment()
            )
        }
    }
}
