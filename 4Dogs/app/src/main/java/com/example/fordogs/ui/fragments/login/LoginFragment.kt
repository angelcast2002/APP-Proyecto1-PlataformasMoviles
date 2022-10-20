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
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoginFragment: Fragment(){
    private lateinit var binding : LoginLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    private lateinit var correo: String
    private lateinit var password: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideNavBar()
        setListeners()
    }

    private fun hideNavBar(){
        val navBar : BottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        val fab : FloatingActionButton = requireActivity().findViewById(R.id.mainActivityFAB)
        navBar.visibility = View.GONE
        fab.visibility = View.GONE
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
        binding.btSignUpLoginLayout.setOnClickListener{

            requireView().findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

    }
}
