package com.example.fordogs.ui.fragments.addevents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fordogs.R
import com.example.fordogs.databinding.FragmentAddEventBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddEventBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideNavBar()
        setListeners()

    }

    private fun hideNavBar() {
        val navBar : BottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        val fab : FloatingActionButton = requireActivity().findViewById(R.id.mainActivityFAB)
        navBar.visibility = View.GONE
        fab.visibility = View.GONE
    }


    private fun setListeners() {
    }

}