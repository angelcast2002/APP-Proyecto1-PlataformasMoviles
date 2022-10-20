package com.example.fordogs.fragments.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R

class CalendarAdapter(
    private val daysOfMonth: ArrayList<String>,
    private val listener: onItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)
        val params = view.layoutParams
        params.height = parent.measuredHeight / 6

        return CalendarViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int)
    {
        holder.dayOfMonth.text = daysOfMonth[position]
    }

    override fun getItemCount(): Int
    {
        return daysOfMonth.size
    }

    interface onItemListener {

        fun onItemClick(dayText: String, position: Int)

    }

}
