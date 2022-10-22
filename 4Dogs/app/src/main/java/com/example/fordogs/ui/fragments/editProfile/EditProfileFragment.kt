package com.example.fordogs.ui.fragments.calendar


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.databinding.EditProfileLayoutBinding
import com.example.fordogs.databinding.RegisterLayoutBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel.Status.*
import com.example.fordogs.ui.fragments.viewModelActivity.RegisterToLoginViewModel
import com.example.fordogs.ui.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EditProfileFragment: BaseFragment<EditProfileLayoutBinding>(){
    private val RegisterToLoginViewModel: RegisterToLoginViewModel by activityViewModels()
    private val EditProfileViewModel: EditProfileViewModel by activityViewModels()

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

        lifecycleScope.launch {
            EditProfileViewModel.imagen.collectLatest { img ->
                setImgUser(img)
            }
        }

        lifecycleScope.launch {
            EditProfileViewModel.status.collectLatest{ status ->
                handleStatus(status)
            }
        }

    }

    private fun setImgUser(img: String) {
        binding.imgPetEditProfileLayout.load(img){
            transformations(CircleCropTransformation())
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            error(R.drawable.ic_error)
            placeholder(R.drawable.ic_download)
        }
    }

    private fun setNameEditText() {
        binding.petNameEdtiProfileLayout.text = name
    }

    private fun setListeners() {
        binding.btSaveDataEditProfale.setOnClickListener{
            EditProfileViewModel.saveChanges()
        }
    }

    fun handleStatus(status: EditProfileViewModel.Status) {
        when(status){
            Editing -> {
                binding.apply {
                    imgPetEditProfileLayout.visibility = View.VISIBLE
                    petNameEdtiProfileLayout.visibility = View.VISIBLE
                    textInputRazaTextEditProfileLayout.visibility = View.VISIBLE
                    textInputPesoTextEditProfileLayout.visibility = View.VISIBLE
                    textInputColorTextEditProfileLayout.visibility = View.VISIBLE
                    textInputAlturaTextEditProfileLayout.visibility = View.VISIBLE
                    textInputLargoTextEditProfileLayout.visibility = View.VISIBLE
                    textInputComidaFavTextEditProfileLayout.visibility  = View.VISIBLE
                    btSaveDataEditProfale.visibility = View.VISIBLE
                    progressEditLayout.visibility = View.GONE
                }
            }
            is Error -> {
                TODO()
            }
            Loading -> {
                binding.apply {
                    imgPetEditProfileLayout.visibility = View.VISIBLE
                    petNameEdtiProfileLayout.visibility = View.VISIBLE
                    textInputRazaTextEditProfileLayout.visibility = View.GONE
                    textInputPesoTextEditProfileLayout.visibility = View.GONE
                    textInputColorTextEditProfileLayout.visibility = View.GONE
                    textInputAlturaTextEditProfileLayout.visibility = View.GONE
                    textInputLargoTextEditProfileLayout.visibility = View.GONE
                    textInputComidaFavTextEditProfileLayout.visibility  = View.GONE
                    btSaveDataEditProfale.visibility = View.GONE
                    progressEditLayout.visibility = View.VISIBLE
                }
            }
            Succes -> {
                requireView().findNavController().navigate(
                    EditProfileFragmentDirections.actionEditProfileFragmentToCalendarFragment()
                )
                EditProfileViewModel.setDefaul()
            }
        }
    }

}