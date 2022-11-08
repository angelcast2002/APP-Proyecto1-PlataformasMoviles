package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.data.remote.RetrofitInstance
import com.example.fordogs.data.remote.dto.PerroTipsDto
import com.example.fordogs.databinding.FragmentCalendarBinding
import com.example.fordogs.di.PerroTipsModule
import com.example.fordogs.ui.util.BaseFragment
import com.example.fordogs.ui.fragments.calendar.CalendarConstants.Companion.selectedDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(), CalendarAdapter.OnItemListener {

    private val calendarVM: CalendarViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthYearText: TextView
    private lateinit var name: String
    override fun getViewBinding() = FragmentCalendarBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarVM.getData()
        setObservables()
        initWidgets()
        setMonthView()
        setListeners()
        showNavBar()
    }

    //Implementar estados
    private fun setObservables() {
        lifecycleScope.launchWhenStarted {
            calendarVM.status.collectLatest { status ->
                handleState(status)
            }
        }
    }


    private fun handleState(status: CalendarViewModel.Status) {
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setListeners() {
        binding.nextMonthButton.setOnClickListener {
            nextMonthAction(monthYearText)
        }
        binding.previousMonthButton.setOnClickListener {
            previousMonthAction(monthYearText)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        monthYearText.text = calendarVM.monthYearFromDate(selectedDate)

        val daysOfMonth = calendarVM.daysInMonthArray(selectedDate)

        val calendarAdapter = CalendarAdapter(daysOfMonth, this)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = calendarAdapter
    }

    private fun initWidgets() {
        recyclerView = binding.calendarRecyclerView
        monthYearText = binding.monthNameHeaderCalendarFragment
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View) {
        selectedDate = selectedDate?.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View) {
        selectedDate = selectedDate?.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(date: LocalDate?, position: Int) {

        if (date != null) {
            selectedDate = date
            setMonthView()
        }

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

}