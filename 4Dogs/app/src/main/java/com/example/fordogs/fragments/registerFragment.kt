package com.example.fordogs.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R

class registerFragment : Fragment(R.layout.register_layout) {
    private lateinit var buttonBack: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            buttonBack = findViewById(R.id.bt_regresar)
        }

        setListeners()
    }

    private fun setListeners() {
        buttonBack.setOnClickListener{
            requireView().findNavController().navigate(
                registerFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }
}