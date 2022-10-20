package com.example.fordogs.ui.fragments.calendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fordogs.databinding.EditProfileLayoutBinding

class EditProfileFragment: Fragment(){

    private lateinit var binding : EditProfileLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditProfileLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }
}