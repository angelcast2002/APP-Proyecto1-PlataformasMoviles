package com.example.fordogs.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.fordogs.R
import com.example.fordogs.data.repository.Firebase.FirebaseRepository
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import com.example.fordogs.ui.fragments.login.LoginViewModel.*
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: BaseFragment<LoginLayoutBinding>(){
    private val logInViewModel: LoginViewModel by viewModels()
    lateinit var  firebaseRepository: FirebaseRepository


    private lateinit var correo: String
    private lateinit var password: String
    override fun getViewBinding() = LoginLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        hideNavBar()
        setListeners()
        setObservables()
    }

    private fun setObservables() {
        lifecycleScope.launch {
            logInViewModel.status.collectLatest { status ->
                handleStatus(status)

            }
        }
    }

    private fun handleStatus(status: Status) {
        when(status){
            Status.Default -> {
                binding.apply {
                    textInputCorreoTextLoginLayout.visibility = View.VISIBLE
                    textInputPasswordTextLoginLayout.visibility = View.VISIBLE
                    btIniciarSesionLoginLayout.visibility = View.VISIBLE
                    btSignUpLoginLayout.visibility = View.VISIBLE
                    progressLoginLayout.visibility = View.GONE
                }
            }
            is Status.Error -> {
                binding.apply {
                    textInputCorreoTextLoginLayout.visibility = View.VISIBLE
                    textInputPasswordTextLoginLayout.visibility = View.VISIBLE
                    btIniciarSesionLoginLayout.visibility = View.VISIBLE
                    btSignUpLoginLayout.visibility = View.VISIBLE
                    progressLoginLayout.visibility = View.GONE
                    Toast.makeText(activity, status.message,
                        Toast.LENGTH_LONG)
                        .show()
                }

                logInViewModel.setDefault()

            }
            Status.Loading -> {
                binding.apply {
                    textInputCorreoTextLoginLayout.visibility = View.GONE
                    textInputPasswordTextLoginLayout.visibility = View.GONE
                    btIniciarSesionLoginLayout.visibility = View.GONE
                    btSignUpLoginLayout.visibility = View.GONE
                    progressLoginLayout.visibility = View.VISIBLE
                }
            }
            is Status.Succes -> {
                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToCalendarFragment()
                )
            }
        }
    }

    private fun setListeners() {
        binding.btIniciarSesionLoginLayout.setOnClickListener {

            correo = binding.textInputCorreoTextLoginlayoutEditText.text.toString()
            password = binding.textInputPasswordTextLoginLayoutEditText.text.toString()


            logInViewModel.firebaseLogingIn(correo, password)



        }



        binding.btSignUpLoginLayout.setOnClickListener {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }
}