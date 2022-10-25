package com.example.fordogs.ui.fragments.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment: BaseFragment<RegisterLayoutBinding>(){

    private val EditProfileViewModel: EditProfileViewModel by activityViewModels()
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
            EditProfileViewModel.nombre.collectLatest {
                name = EditProfileViewModel.nombre.value
            }
        }
    }

    private fun setListeners() {
        binding.btRegresar.setOnClickListener{
            requireActivity().onBackPressed()
        }

        binding.btRegistraseRegisterLayout.setOnClickListener{
            name = binding.textInputUserTextRegisterLayoutEditText.text.toString()
            EditProfileViewModel.nameUser(name)
            requireView().findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToEditProfileFragment()
            )
        }
    }
}
