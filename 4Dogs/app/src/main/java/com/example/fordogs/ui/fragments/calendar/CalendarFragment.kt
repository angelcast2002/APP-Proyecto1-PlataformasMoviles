package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.fordogs.R
import com.example.fordogs.databinding.FragmentCalendarBinding
import com.example.fordogs.ui.fragments.editProfile.EditProfileViewModel
import com.example.fordogs.ui.util.BaseFragment
import com.example.fordogs.ui.util.CalendarConstants.Companion.selectedDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class CalendarFragment : BaseFragment<FragmentCalendarBinding>(), CalendarAdapter.OnItemListener {

    private val EditProfileViewModel: EditProfileViewModel by activityViewModels()
    private val calendarVM: CalendarViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var monthYearText: TextView
    private lateinit var name: String
    override fun getViewBinding() = FragmentCalendarBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservables()
        initWidgets()
        setMonthView()
        setListeners()
        showNavBar()
        setName()
    }

    private fun setObservables() {
        lifecycleScope.launch {
            EditProfileViewModel.imagen.collectLatest { img ->
                setImgUser(img)
            }
        }

        lifecycleScope.launch{
            EditProfileViewModel.nombre.collectLatest { nombre ->
                name = nombre
            }
        }
    }

    private fun setName() {
        binding.userNameCalendarFragment.text = name
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