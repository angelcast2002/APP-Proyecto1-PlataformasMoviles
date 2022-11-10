package com.example.fordogs.ui.fragments.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fordogs.R
import com.example.fordogs.databinding.FragmentEventsBinding
import com.example.fordogs.ui.util.BaseFragment

class EventsFragment : BaseFragment<FragmentEventsBinding>() {

    override fun getViewBinding() = FragmentEventsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNavBar()
    }
}