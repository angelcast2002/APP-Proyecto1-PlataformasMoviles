package com.example.fordogs.fragments.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder>() {

    private val daysOfMonth: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)
        val params = view.layoutParams
        params.height = parent.measuredHeight / 6

        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int)
    {
        holder.dayOfMonth.text = daysOfMonth[position]
    }

    override fun getItemCount(): Int
    {
        return daysOfMonth.size
    }

    interface RecyclerViewDayClickListener {

        fun onDayClick(dayText: String, position: Int)

    }

}
