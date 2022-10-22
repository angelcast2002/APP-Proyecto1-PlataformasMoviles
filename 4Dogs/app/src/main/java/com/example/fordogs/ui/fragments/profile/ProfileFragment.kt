package com.example.fordogs.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.databinding.FragmentProfileBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.fragments.viewModelActivity.RegisterToLoginViewModel
import com.example.fordogs.ui.util.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(){
    private val RegisterToLoginViewModel: RegisterToLoginViewModel by activityViewModels()
    private val viewModel: EditProfileViewModel by activityViewModels()




    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        setListeners()
        setName()

    }

    private fun setObservables() {
        lifecycleScope.launch {
            RegisterToLoginViewModel.name.collectLatest {
                setName()
            }
        }

        lifecycleScope.launch {
            viewModel.imagen.collectLatest { img ->
                setImgUser(img)
            }
        }

    }

    private fun setImgUser(img: String) {
        binding.imgPetProfileLayout.load(img){
            transformations(CircleCropTransformation())
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            error(R.drawable.ic_error)
            placeholder(R.drawable.ic_download)
        }
    }
    private fun setListeners() {
        binding.btEditarPerfil.setOnClickListener{
            requireView().findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment())
        }
    }
    private fun setName() {
        lifecycleScope.launch{
            RegisterToLoginViewModel.name.collectLatest {
                binding.petNameProfileLayout.text = RegisterToLoginViewModel.name.value
            }
        }
    }


}