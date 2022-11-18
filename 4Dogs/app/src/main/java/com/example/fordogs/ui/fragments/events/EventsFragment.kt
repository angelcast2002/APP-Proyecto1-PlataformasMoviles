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
        eventsViewModel.getData()
        setObservables()
        binding.textViewNoTipsEventsLayout.visibility = View.GONE
        binding.imageNoTips.visibility = View.GONE

    }

    private fun setupRecycler() {

        binding.apply{
            recyclerRecyclerActivity.layoutManager = LinearLayoutManager(requireContext())
            recyclerRecyclerActivity.setHasFixedSize(true)
            recyclerRecyclerActivity.adapter = PlaceAdapter(dataToRecyclerView)
            recyclerRecyclerActivity.visibility = View.VISIBLE
        }

    }

    private fun getTips(name:String) {
        eventsViewModel.getTips(name)
        eventsViewModel.pruebaToDefault()
    }

    private fun setObservables() {

        lifecycleScope.launch{
            eventsViewModel.statusTips.collectLatest { status ->
                handleStatusTips(status)
            }
        }

        lifecycleScope.launch {
            eventsViewModel.status.collectLatest { status ->
                handleStatusData(status)
            }
        }

    }

    private fun handleStatusData(status: EventsFragmentViewModel.Status) {
        when(status) {
            is EventsFragmentViewModel.Status.Error -> {

            }
            EventsFragmentViewModel.Status.Loading -> {

            }
            is EventsFragmentViewModel.Status.Succes -> {
                getTips(status.data.raza)
            }
        }
    }


    private fun handleStatusTips(status: EventsFragmentViewModel.StatusTips) {
        when (status) {
            EventsFragmentViewModel.StatusTips.Default -> {
                binding.textViewNoTipsEventsLayout.visibility = View.GONE
                binding.progressEventsLayout.visibility = View.GONE
                binding.imageNoTips.visibility = View.GONE

            }
            is EventsFragmentViewModel.StatusTips.Error -> {
                binding.textViewNoTipsEventsLayout.visibility = View.VISIBLE
                binding.imageNoTips.visibility = View.VISIBLE
                binding.progressEventsLayout.visibility = View.GONE
            }
            is EventsFragmentViewModel.StatusTips.Succes -> {
                binding.progressEventsLayout.visibility = View.GONE
                binding.textViewNoTipsEventsLayout.visibility = View.GONE
                binding.imageNoTips.visibility = View.GONE
                var dataRecyclerView = status.data
                perroTipsRecyclerViewData(dataRecyclerView)
                setupRecycler()
                println(status.data)
            }
            EventsFragmentViewModel.StatusTips.Loading -> {
                binding.progressEventsLayout.visibility = View.VISIBLE
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