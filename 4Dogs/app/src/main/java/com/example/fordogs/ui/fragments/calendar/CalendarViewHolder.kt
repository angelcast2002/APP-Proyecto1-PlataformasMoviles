package com.example.fordogs.ui.fragments.calendar

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R


class CalendarViewHolder(itemView: View,
                         private val listener: CalendarAdapter.onItemListener
) : RecyclerView.ViewHolder(itemView) {

    private val layoutCell : ConstraintLayout = itemView.findViewById(R.id.calendar_cell_layout)
    val dayOfMonth : TextView = itemView.findViewById(R.id.cellDayText)

    init {
        layoutCell.setOnClickListener {
            listener.onItemClick(dayOfMonth.text.toString(), absoluteAdapterPosition)
        }
    }

}