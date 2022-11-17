package com.example.fordogs.ui.fragments.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.remote.RetrofitInstance
import com.example.fordogs.data.remote.dto.PerroTipsRecyclerView
import com.example.fordogs.databinding.FragmentEventsBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.fragments.events.recyclerView.PlaceAdapter
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
    private var dataToRecyclerView: MutableList<PerroTipsRecyclerView> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showNavBar()
        setObservables()
        llamadaPrueba()

    }

    private fun setupRecycler() {

        binding.apply{
            recyclerRecyclerActivity.layoutManager = LinearLayoutManager(requireContext())
            recyclerRecyclerActivity.setHasFixedSize(true)
            recyclerRecyclerActivity.adapter = PlaceAdapter(dataToRecyclerView)
            recyclerRecyclerActivity.visibility = View.VISIBLE
        }

    }

    private fun llamadaPrueba() {
        eventsViewModel.prueba("golden retriever")
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
                var dataRecyclerView = status.data
                perroTipsRecyclerViewData(dataRecyclerView)
                setupRecycler()
                println(status.data)
            }
        }
    }

    private fun perroTipsRecyclerViewData(dataRecyclerView: PerroTips) {
        dataToRecyclerView.add(
            PerroTipsRecyclerView(0,dataRecyclerView.good_with_children)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(1,dataRecyclerView.good_with_other_dogs)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(2,dataRecyclerView.playfulness)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(3,dataRecyclerView.protectiveness)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(4,dataRecyclerView.trainability)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(5,dataRecyclerView.energy)
        )
        dataToRecyclerView.add(
            PerroTipsRecyclerView(6,dataRecyclerView.grooming)
        )
    }
}