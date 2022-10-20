package com.example.fordogs.fragments.calendar

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.databinding.FragmentCalendarBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment(), CalendarAdapter.onItemListener{

    private lateinit var binding: FragmentCalendarBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var selectedDate: LocalDate
    private lateinit var monthYearText: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidgets()
        selectedDate = LocalDate.now()
        setMonthView()
        setListeners()
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
        monthYearText.text = monthYearFromDate(selectedDate)

        val daysOfMonth = daysInMonthArray(selectedDate)

        val calendarAdapter = CalendarAdapter(daysOfMonth, this)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 7)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {

        val daysInMonthArray = arrayListOf<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()

        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {

            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }

        }

        return daysInMonthArray
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthYearFromDate(date: LocalDate): String {

        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")

        return date.format(formatter)
    }

    private fun initWidgets() {
        recyclerView = binding.calendarRecyclerView
        monthYearText = binding.monthNameHeaderCalendarFragment
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun nextMonthAction(view: View) {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun previousMonthAction(view: View) {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(dayText: String, position: Int) {

        if (dayText != "") {
            val message = "Fecha seleccionada $dayText ${monthYearFromDate(selectedDate)}"

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

    }

}