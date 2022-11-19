package com.example.fordogs.ui.fragments.editProfile


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.databinding.EditProfileLayoutBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel.Status.*
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<EditProfileLayoutBinding>(){

    private val EditProfileViewModel: EditProfileViewModel by viewModels()
    private lateinit var userPerroInfo: UserPerro

    override fun getViewBinding() = EditProfileLayoutBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData()
        setObservables()
        hideNavBar()
        setListeners()
    }

    private fun getData() {
        EditProfileViewModel.getData()
    }

    private fun setObservables() {

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

    private fun setListeners() {
        binding.btSaveDataEditProfile.setOnClickListener{
            savedChanges()
            EditProfileViewModel.saveChanges(userPerroInfo)
        }
    }

    private fun savedChanges(){
        userPerroInfo = UserPerro(
                "0", //Hay que cambiarlo
                binding.textInputNombreTextEditProfilelayoutEditText.text.toString(),
                binding.textInputRazaTextEditProfilelayoutEditText.text.toString(),
                binding.textInputPesoTextEditProfilelayoutEditText.text.toString().toInt(),
                binding.textInputColorTextEditProfilelayoutEditText.text.toString(),
                binding.textInputAlturaTextEditProfilelayoutEditText.text.toString().toInt(),
                binding.textInputLargoTextEditProfilelayoutEditText.text.toString().toInt(),
                binding.textInputComidaFavTextEditProfilelayoutEditText.text.toString(),
                binding.textInputUrlImageEditProfilelayoutEditText.text.toString()
        )

    }

    private fun handleStatus(status: EditProfileViewModel.Status) {
        when(status){
            is Editing -> {
                val datos = status.data
                binding.apply {
                    imgPetEditProfileLayout.visibility = View.VISIBLE
                    setImgUser(datos.imagen)

                    petNameEdtiProfileLayout.visibility = View.VISIBLE
                    textInputNombreTextEditProfilelayoutEditText.setText(datos.nombre)

                    textInputRazaTextEditProfileLayout.visibility = View.VISIBLE
                    textInputRazaTextEditProfilelayoutEditText.setText(datos.raza)

                    textInputPesoTextEditProfileLayout.visibility = View.VISIBLE
                    textInputPesoTextEditProfilelayoutEditText.setText(datos.peso.toString())

                    textInputColorTextEditProfileLayout.visibility = View.VISIBLE
                    textInputColorTextEditProfilelayoutEditText.setText(datos.color)

                    textInputAlturaTextEditProfileLayout.visibility = View.VISIBLE
                    textInputAlturaTextEditProfilelayoutEditText.setText(datos.alto.toString())

                    textInputLargoTextEditProfileLayout.visibility = View.VISIBLE
                    textInputLargoTextEditProfilelayoutEditText.setText(datos.largo.toString())

                    textInputComidaFavTextEditProfileLayout.visibility  = View.VISIBLE
                    textInputComidaFavTextEditProfilelayoutEditText.setText(datos.comidaFav)

                    textInputUrlImageEditProfilelayoutEditText.visibility  = View.VISIBLE
                    textInputUrlImageEditProfilelayoutEditText.setText(datos.imagen)

                    btSaveDataEditProfile.visibility = View.VISIBLE

                    progressEditLayout.visibility = View.GONE
                }
            }
            is Error -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
                EditProfileViewModel.setEditing()
            }
            Loading -> {
                binding.apply {
                    imgPetEditProfileLayout.visibility = View.VISIBLE
                    imgPetEditProfileLayout.visibility = View.GONE
                    petNameEdtiProfileLayout.visibility = View.GONE
                    textInputRazaTextEditProfileLayout.visibility = View.GONE
                    textInputPesoTextEditProfileLayout.visibility = View.GONE
                    textInputColorTextEditProfileLayout.visibility = View.GONE
                    textInputAlturaTextEditProfileLayout.visibility = View.GONE
                    textInputLargoTextEditProfileLayout.visibility = View.GONE
                    textInputComidaFavTextEditProfileLayout.visibility  = View.GONE
                    btSaveDataEditProfile.visibility = View.GONE
                    progressEditLayout.visibility = View.VISIBLE
                }
            }
            is Succes -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()

                requireView().findNavController().navigate(
                    EditProfileFragmentDirections.actionEditProfileFragmentToProfileFragment()
                )

                EditProfileViewModel.setDefault()
            }
            Default -> {

            }
        }
    }

}