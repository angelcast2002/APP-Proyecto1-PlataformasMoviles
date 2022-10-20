package com.example.fordogs.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.example.fordogs.databinding.EditProfileLayoutBinding
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoginFragment: BaseFragment<LoginLayoutBinding>(){

    private lateinit var correo: String
    private lateinit var password: String
    override fun getViewBinding() = LoginLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideNavBar()
        setListeners()
    }

    private fun setListeners() {
        binding.btIniciarSesionLoginLayout.setOnClickListener{

            correo = binding.textInputCorreoTextLoginlayoutEditText.text.toString()
            password = binding.textInputPasswordTextLoginLayoutEditText.text.toString()

            if (correo == "ejemplo@uvg" && password == "ejemplo") { //hay que cambiarlo cuando tengamos el api
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
