package com.example.fordogs.ui.fragments.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.fordogs.data.repository.authFirebase.AuthRepository
import com.example.fordogs.data.repository.authFirebase.AuthRepositoryImplementation
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import com.example.fordogs.ui.fragments.login.LoginViewModel.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment: BaseFragment<LoginLayoutBinding>(){
    private val logInViewModel: LoginViewModel by viewModels()

    private lateinit var authRepository: AuthRepository
    private lateinit var email: String
    private lateinit var password: String
    override fun getViewBinding() = LoginLayoutBinding.inflate(layoutInflater)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authRepository=AuthRepositoryImplementation(
            api = FirebaseAuthApiImplementation()
        )

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
            Status.Succes -> {

                requireView().findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToCalendarFragment()
                )

                logInViewModel.setDefault()

            }
        }
    }

    private fun setListeners(status: LoginViewModel.Status) {
        binding.btIniciarSesionLoginLayout.setOnClickListener{

            val email= binding.textInputCorreoTextLoginlayoutEditText.text.toString()
            val password = binding.textInputPasswordTextLoginLayoutEditText.text.toString()
            logInViewModel.loading(email, password)

            lifecycleScope.launch() {
                val id = authRepository.signInWithEmailAndPasword(email, password)
                if(id != null){
                    Toast.makeText(activity, status.message,
                        Toast.LENGTH_LONG)
                        .show()
                }else{
                    Toast.makeText(activity, status.message,
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        binding.btSignUpLoginLayout.setOnClickListener {
            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }
}
