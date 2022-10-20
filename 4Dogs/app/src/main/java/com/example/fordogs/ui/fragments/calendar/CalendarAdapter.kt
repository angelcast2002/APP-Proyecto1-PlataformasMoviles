package com.example.fordogs.ui.fragments.calendar

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import com.example.fordogs.ui.util.CalendarConstants.Companion.selectedDate
import java.time.LocalDate
import kotlin.collections.ArrayList

class CalendarAdapter(
    private val days: ArrayList<LocalDate?>,
    private val listener: OnItemListener
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder
    {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)
        val params = view.layoutParams
        params.height = parent.measuredHeight / 6

        return CalendarViewHolder(view, listener, days)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int)
    {
        val date = days[position]

        if (date == null) {
            holder.dayOfMonth.text = ""
        } else {
            holder.dayOfMonth.text = date.dayOfMonth.toString()
            if (date == selectedDate) {
                holder.parentView.setBackgroundResource(R.drawable.calendar_selected_cell_background)
            }
        }
    }

    override fun getItemCount(): Int
    {
        return days.size
    }

    interface OnItemListener {

        fun onItemClick(date: LocalDate?, position: Int)

    }

}
