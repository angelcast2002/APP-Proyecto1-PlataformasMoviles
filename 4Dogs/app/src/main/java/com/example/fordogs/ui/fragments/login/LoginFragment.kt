package com.example.fordogs.ui.fragments.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        logInViewModel.checkIsLogged(obtainContext())
        hideNavBar()
        setListeners()
        setObservables()
    }

    private fun setObservables() {
        lifecycleScope.launch {
            logInViewModel.isLogged.collectLatest { isLogged ->
                handleIsLogged(isLogged)
            }
        }
        lifecycleScope.launchWhenStarted {
            logInViewModel.status.collectLatest { status ->
                handleStatus(status)

            }
        }
    }

    private fun handleIsLogged(logged: Logged) {
        when(logged){
            Logged.NotLogged -> {

            }
            Logged.Succes -> {
                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToCalendarFragment()
                )
                logInViewModel.setDefaultLogged()
            }
        }
    }

    private fun handleStatus(status: LoginViewModel.Status) {
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
                obtainContext()
                logInViewModel.saveLog(obtainContext())

                changeLayout()

            }
        }
    }

    private fun changeLayout(){
        requireView().findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToCalendarFragment()
        )

    }

    private fun obtainContext(): Context {
        return requireContext().applicationContext
    }

    private fun setListeners() {
        binding.btIniciarSesionLoginLayout.setOnClickListener {

            correo = binding.textInputCorreoTextLoginlayoutEditText.text.toString()
            correo = correo.replace(" ", "")
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
