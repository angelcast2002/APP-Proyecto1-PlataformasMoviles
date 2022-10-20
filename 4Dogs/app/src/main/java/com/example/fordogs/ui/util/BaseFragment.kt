package com.example.fordogs.ui.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.fordogs.R
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

abstract class BaseFragment<VB : ViewBinding>: Fragment() {

    protected lateinit var binding: VB
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun init() {
        binding = getViewBinding()
    }

    fun hideNavBar() {
        val navBar : BottomAppBar = requireActivity().findViewById(R.id.bottomAppBar)
        val fab : FloatingActionButton = requireActivity().findViewById(R.id.mainActivityFAB)
        navBar.visibility = View.GONE
        fab.visibility = View.GONE
    }

}