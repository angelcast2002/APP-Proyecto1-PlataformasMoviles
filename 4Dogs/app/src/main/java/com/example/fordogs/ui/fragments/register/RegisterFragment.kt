package com.example.fordogs.ui.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.fordogs.R
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegisterFragment: Fragment(){
    private lateinit var binding: RegisterLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        binding.btRegresar.setOnClickListener{
            requireView().findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }
}
