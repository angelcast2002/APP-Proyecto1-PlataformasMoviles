package com.example.fordogs.ui.fragments.calendar

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.R
import java.time.LocalDate


class CalendarViewHolder(itemView: View,
                         private val listener: CalendarAdapter.OnItemListener,
                         private val days: ArrayList<LocalDate?>
) : RecyclerView.ViewHolder(itemView) {

    val parentView: View = itemView.findViewById(R.id.calendar_cell_layout)
    private val layoutCell : ConstraintLayout = itemView.findViewById(R.id.calendar_cell_layout)
    val dayOfMonth : TextView = itemView.findViewById(R.id.cellDayText)

    init {
        layoutCell.setOnClickListener {
            listener.onItemClick(days[absoluteAdapterPosition], absoluteAdapterPosition)
        }
    }

}