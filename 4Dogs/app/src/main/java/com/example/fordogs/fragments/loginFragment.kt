package com.example.fordogs.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.google.android.material.textfield.TextInputLayout

class loginFragment: Fragment(R.layout.login_layout) {

    private lateinit var buttonIniciarSesion: Button
    private lateinit var resultadoAPI: Any //Esto hay que cambiarlo al implementar la api
    private lateinit var textCorreo: EditText
    private lateinit var textPassword: EditText
    private lateinit var correo: String
    private lateinit var password: String
    private lateinit var buttonNuevoUsuario: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            buttonIniciarSesion = findViewById(R.id.bt_iniciarSesion_loginLayout)
            textCorreo = findViewById(R.id.textInput_correoText_loginlayout_editText)
            textPassword = findViewById(R.id.textInput_passwordText_loginLayout_editText)
            buttonNuevoUsuario = findViewById(R.id.bt_signUp_loginLayout)

        }

        setListeners()
    }

    private fun setListeners() {
        buttonIniciarSesion.setOnClickListener{

            correo = textCorreo.text.toString()
            password = textPassword.text.toString()

            if (correo == "ejemplo@uvg" && password == "ejemplo") { //hay que cambiarlo cuando tengamos el api
                requireView().findNavController().navigate(
                    loginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }
            else {
                Toast.makeText(activity, getString(R.string.invalidPasswordOrEmail), Toast.LENGTH_LONG)
                    .show()
            }
        }
        buttonNuevoUsuario.setOnClickListener{

            requireView().findNavController().navigate(
                loginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

    }
}