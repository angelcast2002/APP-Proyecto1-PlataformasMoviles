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
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.ALTO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.COLOR
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.COMIDA_FAV
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.IMG_PERRO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.LARGO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.NOMBRE
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.PESO
import com.example.fordogs.ui.fragments.editProfile.EditProfileConstants.Companion.RAZA
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel.Status.*
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProfileFragment: BaseFragment<EditProfileLayoutBinding>(){

    private val EditProfileViewModel: EditProfileViewModel by viewModels()
    private lateinit var Id : String
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
        val nombre = binding.textInputNombreTextEditProfilelayoutEditText.text.toString()
        val raza = binding.textInputRazaTextEditProfilelayoutEditText.text.toString()
        val peso = binding.textInputPesoTextEditProfilelayoutEditText.text.toString()
        val color = binding.textInputColorTextEditProfilelayoutEditText.text.toString()
        val alto = binding.textInputAlturaTextEditProfilelayoutEditText.text.toString()
        val largo = binding.textInputLargoTextEditProfilelayoutEditText.text.toString()
        val comidaFav = binding.textInputComidaFavTextEditProfilelayoutEditText.text.toString()
        val imagen = binding.textInputUrlImageEditProfilelayoutEditText.text.toString()
        var arrayList: ArrayList<String> = arrayListOf()
        arrayList.apply {
            add(nombre)
            add(raza)
            add(peso)
            add(color)
            add(alto)
            add(largo)
            add(comidaFav)
            add(imagen)
        }
        arrayList = changeNullValues(arrayList)
        userPerroInfo = UserPerro(
            id = Id,
            nombre = arrayList[0],
            raza = arrayList[1],
            peso = arrayList[2].toInt(),
            color = arrayList[3],
            alto = arrayList[4].toInt(),
            largo = arrayList[5].toInt(),
            comidaFav = arrayList[6],
            imagen = arrayList[7]
        )

    }

    private fun changeNullValues(arrayList: ArrayList<String>): ArrayList<String>{
        var contador = 0
        for (value in arrayList){
            if (value == "") {
                when (contador) {
                    0 -> {
                        arrayList[contador] = NOMBRE
                    }
                    1 -> {
                        arrayList[contador] = RAZA
                    }
                    2 -> {
                        arrayList[contador] = PESO.toString()
                    }
                    3 -> {
                        arrayList[contador] = COLOR
                    }
                    4 -> {
                        arrayList[contador] = ALTO.toString()
                    }
                    5 -> {
                        arrayList[contador] = LARGO.toString()
                    }
                    6 -> {
                        arrayList[contador] = COMIDA_FAV
                    }
                    7 -> {
                        arrayList[contador] = IMG_PERRO
                    }
                }
            }
            contador++

        }
        return arrayList
    }

    private fun handleStatus(status: EditProfileViewModel.Status) {
        when(status){
            is Editing -> {
                val datos = status.data
                Id = datos.id
                binding.apply {
                    urlImageEdtiProfileLayout.visibility = View.VISIBLE
                    
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
                    Toast.LENGTH_SHORT
                ).show()
                EditProfileViewModel.setEditing()
            }
            Loading -> {
                binding.apply {
                    urlImageEdtiProfileLayout.visibility = View.GONE
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
                    Toast.LENGTH_SHORT
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