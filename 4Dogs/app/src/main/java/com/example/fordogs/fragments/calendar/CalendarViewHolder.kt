package com.example.fordogs.fragments.calendar

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R


class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val dayOfMonth : TextView = itemView.findViewById(R.id.cellDayText)

}