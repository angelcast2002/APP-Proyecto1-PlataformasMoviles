package com.example.fordogs.fragments.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R


class CalendarViewHolder(itemView: View,
                         private val listener: CalendarAdapter.onItemListener
) : RecyclerView.ViewHolder(itemView) {

    val dayOfMonth : TextView = itemView.findViewById(R.id.cellDayText)

    fun onClick(view: View) {
        listener.onItemClick(dayOfMonth.text.toString(), absoluteAdapterPosition)
    }

}