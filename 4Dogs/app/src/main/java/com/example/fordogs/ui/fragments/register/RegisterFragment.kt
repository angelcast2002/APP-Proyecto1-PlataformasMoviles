package com.example.fordogs.ui.fragments.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.fordogs.data.local.entity.userPerro
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.example.fordogs.ui.fragments.register.RegisterFragmentViewModel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: BaseFragment<RegisterLayoutBinding>(){

    private val RegisterFragmentViewModel: RegisterFragmentViewModel by viewModels()
    private lateinit var userPerroInfo: userPerro

    override fun getViewBinding() = RegisterLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        hideNavBar()
        setListeners()
    }

    private fun setObservables() {
        lifecycleScope.launch{
            RegisterFragmentViewModel.status.collectLatest { status ->
                handleStatus(status)
            }
        }
    }

    private fun handleStatus(status: RegisterFragmentViewModel.Status) {
        when(status){
            Status.Default -> {
                binding.apply {
                    textInputCorreoTextRegisterLayout.visibility = View.VISIBLE
                    textInputPasswordTextRegisterLayout.visibility = View.VISIBLE
                    textInputUserTextRegisterLayout.visibility = View.VISIBLE

                    btRegistraseRegisterLayout.visibility = View.VISIBLE

                    progressRegisterLayout.visibility = View.GONE
                }
            }
            is Status.Error -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
                RegisterFragmentViewModel.setDefault()
            }
            Status.Loading -> {
                binding.apply {
                    textInputCorreoTextRegisterLayout.visibility = View.GONE
                    textInputPasswordTextRegisterLayout.visibility = View.GONE
                    textInputUserTextRegisterLayout.visibility = View.GONE

                    btRegistraseRegisterLayout.visibility = View.GONE

                    progressRegisterLayout.visibility = View.VISIBLE
                }
            }
            is Status.Succes -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
                RegisterFragmentViewModel.setDefault()
            }
        }
    }

    private fun setListeners() {
        binding.btRegresar.setOnClickListener{
            requireActivity().onBackPressed()
        }

        binding.btRegistraseRegisterLayout.setOnClickListener{
            savedChanges()
            RegisterFragmentViewModel.saveChanges(userPerroInfo)
            requireView().findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToEditProfileFragment()
            )
        }
    }

    private fun savedChanges() {
        userPerroInfo = userPerro(
            "",
            binding.textInputUserTextRegisterLayoutEditText.text.toString(),
            "",
            0,
            "",
            0,
            0,
            ""
        )
    }
}
