package com.example.fordogs.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.example.fordogs.databinding.LoginLayoutBinding
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.util.BaseFragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterFragment: BaseFragment<RegisterLayoutBinding> (){


    override fun getViewBinding() = RegisterLayoutBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideNavBar()
        setListeners()
    }

    private fun setListeners() {
        binding.btRegresar.setOnClickListener{
            requireView().findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }
}
