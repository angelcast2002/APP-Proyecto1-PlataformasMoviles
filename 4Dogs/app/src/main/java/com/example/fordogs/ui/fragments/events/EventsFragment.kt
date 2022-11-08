package com.example.fordogs.ui.fragments.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.fordogs.R
import com.example.fordogs.data.remote.RetrofitInstance
import com.example.fordogs.databinding.FragmentEventsBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventsFragment : BaseFragment<FragmentEventsBinding>() {

    private val eventsViewModel: EventsFragmentViewModel by viewModels()
    override fun getViewBinding() = FragmentEventsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNavBar()
        setObservables()
        llamadaPrueba()
    }

    private fun llamadaPrueba() {
        eventsViewModel.prueba("perra")
        eventsViewModel.pruebaToDefault()
    }

    private fun setObservables() {

        lifecycleScope.launch{
            eventsViewModel.statusTips.collectLatest { status ->
                handleStatusTips(status)
            }
        }

    }



    private fun handleStatusTips(status: EventsFragmentViewModel.StatusTips) {
        when (status) {
            EventsFragmentViewModel.StatusTips.Default -> {

            }
            is EventsFragmentViewModel.StatusTips.Error -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            is EventsFragmentViewModel.StatusTips.Succes -> {
                Toast.makeText(
                    requireContext(),
                    status.data.name,
                    Toast.LENGTH_LONG
                ).show()
                println(status.data)
            }
        }
    }
}