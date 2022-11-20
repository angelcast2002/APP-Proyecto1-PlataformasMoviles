package com.example.fordogs.ui.fragments.profile

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
import com.example.fordogs.databinding.FragmentProfileBinding
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(){

    private val viewModel: ProfileViewModel by viewModels()

    override fun getViewBinding() = FragmentProfileBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getData()
        setObservables()
        setListeners()
        showNavBar()

    }

    private fun getData() {
        viewModel.getData()
    }

    private fun setObservables() {
        lifecycleScope.launch {
            viewModel.status.collectLatest { status ->
                handleStatus(status)
            }
        }

    }

    private fun handleStatus(status: ProfileViewModel.Status) {
        when(status) {

            is ProfileViewModel.Status.Error -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
                viewModel.setLoading()
            }
            ProfileViewModel.Status.Loading -> {
                binding.apply {

                    progressProfileLayout.visibility = View.VISIBLE

                    btEditarPerfil.visibility = View.GONE
                    imgPetProfileLayout.visibility = View.GONE

                    textRazaTextProfileLayout.visibility = View.GONE
                    textDatoRazaTextProfileLayout.visibility = View.GONE

                    textPesoTextProfileLayout.visibility = View.GONE
                    textDatoPesoTextProfileLayout.visibility = View.GONE

                    textColorTextProfileLayout.visibility = View.GONE
                    textDatoColorTextProfileLayout.visibility = View.GONE

                    textLargoTextProfileLayout.visibility = View.GONE
                    textDatoLargoTextProfileLayout.visibility = View.GONE

                    textAltoTextProfileLayout.visibility = View.GONE
                    textDatoLargoTextProfileLayout.visibility = View.GONE

                    textComidaFavTextProfileLayout.visibility = View.GONE
                    textDatoComidaFavTextProfileLayout.visibility = View.GONE


                }
            }
            is ProfileViewModel.Status.Succes -> {
                val data = status.data
                setImgUser(data.imagen)
                binding.apply {

                    progressProfileLayout.visibility = View.GONE

                    btEditarPerfil.visibility = View.VISIBLE

                    imgPetProfileLayout.visibility = View.VISIBLE

                    petNameProfileLayout.visibility = View.VISIBLE
                    petNameProfileLayout.text = data.nombre

                    textRazaTextProfileLayout.visibility = View.VISIBLE
                    textDatoRazaTextProfileLayout.visibility = View.VISIBLE
                    textDatoRazaTextProfileLayout.text = data.raza

                    textPesoTextProfileLayout.visibility = View.VISIBLE
                    textDatoPesoTextProfileLayout.visibility = View.VISIBLE
                    textDatoPesoTextProfileLayout.text = data.peso.toString()

                    textColorTextProfileLayout.visibility = View.VISIBLE
                    textDatoColorTextProfileLayout.visibility = View.VISIBLE
                    textDatoColorTextProfileLayout.text = data.color

                    textLargoTextProfileLayout.visibility = View.VISIBLE
                    textDatoLargoTextProfileLayout.visibility = View.VISIBLE
                    textDatoLargoTextProfileLayout.text = data.largo.toString()

                    textAltoTextProfileLayout.visibility = View.VISIBLE
                    textDatoAltoTextProfileLayout.visibility = View.VISIBLE
                    textDatoAltoTextProfileLayout.text = data.alto.toString()

                    textComidaFavTextProfileLayout.visibility = View.VISIBLE
                    textDatoComidaFavTextProfileLayout.visibility = View.VISIBLE
                    textDatoComidaFavTextProfileLayout.text = data.comidaFav


                }
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

}