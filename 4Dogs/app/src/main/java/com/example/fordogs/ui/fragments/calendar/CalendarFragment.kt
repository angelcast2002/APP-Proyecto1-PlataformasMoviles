package com.example.fordogs.ui.fragments.calendar

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import com.example.fordogs.ui.fragments.calendar.eventRecyclerView.EventOptionsListener
import com.example.fordogs.ui.fragments.calendar.eventRecyclerView.EventsAdapter
import com.example.fordogs.ui.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(), EventOptionsListener {

    private val calendarVM: CalendarViewModel by viewModels()
    private val eventsVM: EventsManagementViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventsAdapter
    private var events : List<Event> = ArrayList<Event>()
    override fun getViewBinding() = FragmentCalendarBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarVM.getData()
        setObservables()
        initWidgets()
        showNavBar()
        setUpAdapter()
    }

    //Implementar estados
    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            calendarVM.status.collectLatest { status ->
                handlePerroInfoState(status)
            }
            calendarVM.eventStatus.collectLatest { status ->
                handleEventState(status)
            }
        }
    }

    private fun handleEventState(eventStatus: CalendarViewModel.EventStatus) {
        when (eventStatus) {
            is CalendarViewModel.EventStatus.Error -> {
                Toast.makeText(
                    requireContext(),
                    eventStatus.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            CalendarViewModel.EventStatus.Loading -> {
                binding.calendarRecyclerView.visibility = View.GONE
            }
            is CalendarViewModel.EventStatus.Success -> {
                binding.calendarRecyclerView.visibility = View.VISIBLE
                events = eventStatus.data
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

    private fun setUpAdapter() {
        eventAdapter = EventsAdapter(context = MainActivity(),this, events, this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
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

    override fun deleteEventFromId(eventId: Int) {
        calendarVM.deleteEvent(eventId)
    }


}