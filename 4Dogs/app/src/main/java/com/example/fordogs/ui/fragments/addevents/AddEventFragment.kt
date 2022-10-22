package com.example.fordogs.ui.fragments.addevents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fordogs.R
import com.example.fordogs.databinding.FragmentAddEventBinding
import com.example.fordogs.ui.util.BaseFragment


class AddEventFragment : BaseFragment<FragmentAddEventBinding>() {

    override fun getViewBinding() = FragmentAddEventBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideNavBar()

    }

}