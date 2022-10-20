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
import com.example.fordogs.R
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment: BaseFragment<LoginLayoutBinding>(){
    private val logInViewModel: LoginViewModel by viewModels()


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

    private fun handleStatus(status: LoginViewModel.Status) {
        when(status){
            LoginViewModel.Status.Default -> {
                TODO()
            }
            is LoginViewModel.Status.Error -> TODO()
            LoginViewModel.Status.Loading -> TODO()
            LoginViewModel.Status.Succes -> TODO()
        }
    }

    private fun setListeners() {
        binding.btIniciarSesionLoginLayout.setOnClickListener{

            correo = binding.textInputCorreoTextLoginlayoutEditText.text.toString()
            password = binding.textInputPasswordTextLoginLayoutEditText.text.toString()

            if (correo == "a" && password == "a") { //hay que cambiarlo cuando tengamos el api
                /*requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )*/
            }
            else {
                Toast.makeText(activity, getString(R.string.invalidPasswordOrEmail), Toast.LENGTH_LONG)
                    .show()
            }
        }
        binding.btSignUpLoginLayout.setOnClickListener { view ->
            view.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }
}
