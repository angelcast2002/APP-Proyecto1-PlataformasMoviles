package com.example.fordogs.fragments.register

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class registerFragment : Fragment(R.layout.register_layout) {
    private lateinit var buttonBack: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.apply {
            buttonBack = findViewById(R.id.bt_regresar)
        }

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
        buttonBack.setOnClickListener{
            requireView().findNavController().navigate(
                registerFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }
}