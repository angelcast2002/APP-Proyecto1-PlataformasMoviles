package com.example.fordogs.ui.fragments.calendar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.databinding.FragmentCalendarBinding
import com.example.fordogs.ui.MainActivity
import com.example.fordogs.ui.fragments.addevents.EventsManagementViewModel
import com.example.fordogs.ui.fragments.calendar.eventRecyclerView.EmptyDataObserver
import com.example.fordogs.ui.fragments.calendar.eventRecyclerView.EventOptionsListener
import com.example.fordogs.ui.fragments.calendar.eventRecyclerView.EventsAdapter
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(), EventOptionsListener {

    private val calendarVM: CalendarViewModel by viewModels()
    private val eventsVM: EventsManagementViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventsAdapter
    private var events : List<Event> = ArrayList()
    override fun getViewBinding() = FragmentCalendarBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        calendarVM.getData()
        calendarVM.getEvents()
        setObservables()
        initWidgets()
        showNavBar()
        setUpViews()
    }

    //Implementar estados
    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            calendarVM.status.collectLatest { status ->
                handlePerroInfoState(status)
            }
        }
        lifecycleScope.launchWhenStarted {
            calendarVM.eventStatus.collectLatest { status ->
                handleEventState(status)
            }
        }
    }

    private fun handleEventState(eventStatus: CalendarViewModel.EventStatus) {
        when (eventStatus) {
            is CalendarViewModel.EventStatus.Error -> {
                binding.apply {
                    eventListProgressBar.visibility = View.GONE
                    recyclerConstraintLayout.visibility = View.VISIBLE
                }
                val message = eventStatus.message
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
            CalendarViewModel.EventStatus.Loading -> {
                binding.apply {
                    eventListProgressBar.visibility = View.VISIBLE
                    recyclerConstraintLayout.visibility = View.GONE
                }
            }
            is CalendarViewModel.EventStatus.Deleted -> {
                binding.apply {
                    eventListProgressBar.visibility = View.GONE
                    recyclerConstraintLayout.visibility = View.VISIBLE
                }
                val message = eventStatus.message
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                refreshFragment()
            }
            is CalendarViewModel.EventStatus.Success -> {
                binding.apply{
                    eventListProgressBar.visibility = View.GONE
                    recyclerConstraintLayout.visibility = View.VISIBLE
                }
                events = eventStatus.data
                setUpViews()
            }
        }
    }

    private fun handlePerroInfoState(status: CalendarViewModel.Status) {

        when(status) { //implementar stados
            is CalendarViewModel.Status.Error -> {
                Toast.makeText(
                    requireContext(),
                    status.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            CalendarViewModel.Status.Loading -> {
                //dummy
                binding.userNameCalendarFragment.visibility = View.GONE
                //dummy
            }
            is CalendarViewModel.Status.Succes -> {
                val data = status.data
                setImgUser(data.imagen)
                binding.apply {
                    binding.userNameCalendarFragment.visibility = View.VISIBLE
                    userNameCalendarFragment.text = data.nombre
                }
            }
        }
    }

    private fun setUpViews() {
        eventAdapter = EventsAdapter(activity as MainActivity,this, events, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
        // Here
        val emptyDataObserver = EmptyDataObserver(recyclerView,
            view?.findViewById(R.id.emptyCalendarLayout)
        )
        eventAdapter.registerAdapterDataObserver(emptyDataObserver)
    }

    private fun initWidgets() {
        recyclerView = binding.calendarRecyclerView
    }

    private fun setImgUser(img: String) {
        binding.profilepicCalendarFragment.load(img){
            transformations(CircleCropTransformation())
            diskCachePolicy(CachePolicy.ENABLED)
            memoryCachePolicy(CachePolicy.ENABLED)
            error(R.drawable.ic_error)
            placeholder(R.drawable.ic_download)
        }
    }

    private fun refreshFragment() {
        navController.popBackStack()
        navController.navigate(R.id.calendarFragment)
    }

    override fun deleteEventFromId(eventId: Int) {
        calendarVM.deleteEvent(eventId)
    }


}