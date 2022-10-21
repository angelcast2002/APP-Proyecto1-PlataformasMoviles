package com.example.fordogs.ui.fragments.calendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.fordogs.databinding.EditProfileLayoutBinding
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.fragments.viewModelActivity.RegisterToLoginViewModel
import com.example.fordogs.ui.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditProfileFragment: BaseFragment<EditProfileLayoutBinding>(){
    private val RegisterToLoginViewModel: RegisterToLoginViewModel by activityViewModels()
    lateinit var name: String

    override fun getViewBinding() = EditProfileLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        hideNavBar()
        setListeners()
        setNameEditText()


    }

    private fun setObservables() {
        lifecycleScope.launch{
            RegisterToLoginViewModel.name.collectLatest {
                name = RegisterToLoginViewModel.name.value
            }
        }
    }

    private fun setNameEditText() {
        binding.petNameEdtiProfileLayout.text = name
    }

    private fun setListeners() {
        binding.btSaveDataEditProfale.setOnClickListener{
            requireView().findNavController().navigate(
                EditProfileFragmentDirections.actionEditProfileFragmentToCalendarFragment()
            )
        }
    }

}