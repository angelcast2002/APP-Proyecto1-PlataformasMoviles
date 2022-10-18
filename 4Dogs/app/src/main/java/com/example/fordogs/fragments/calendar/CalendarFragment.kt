package com.example.fordogs.fragments.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.databinding.CalendarEventItemViewBinding
import com.example.fordogs.databinding.FragmentCalendarBinding
import java.time.LocalDate

data class Event(val id: String, val text: String, val date: LocalDate)

class CalendarEventsAdapter(val onClick: (Event) -> Unit) : RecyclerView.Adapter<CalendarEventsAdapter.CalendarEventsViewHolder>() {

    val events = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarEventsViewHolder {
        return CalendarEventsViewHolder(
            CalendarEventItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: CalendarEventsViewHolder, position: Int) {
        viewHolder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    inner class CalendarEventsViewHolder(private val binding: CalendarEventItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClick(events[bindingAdapterPosition])
            }
        }
        fun bind(event: Event) {
            binding.itemEventText.text = event.text
        }
    }

}

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

}