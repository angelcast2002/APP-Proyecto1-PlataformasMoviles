package com.example.fordogs.ui.fragments.calendar.eventRecyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.fordogs.data.local.entity.Event
import com.example.fordogs.databinding.EventCardBinding
import java.text.SimpleDateFormat
import java.util.*

class EventViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root) {

    var dateFormat = SimpleDateFormat("EE dd MMM yyyy", Locale.US)
    var inputDateFormat = SimpleDateFormat("dd-M-yyyy", Locale.US)
    var date: Date? = null
    private var outputDateString: String? = ""

    fun setData(event: Event) {

        binding.title.text = event.eventTitle
        binding.description.text = event.eventDescription
        binding.time.text = event.lastAlarm
        binding.status.text = if (event.isComplete) "COMPLETADO" else "PENDIENTE"

        try {
            date = event.eventDate?.let { inputDateFormat.parse(it) }
            outputDateString = date?.let { dateFormat.format(it) }
            val items1 = outputDateString?.split(" ")?.toTypedArray()
            val day = items1?.get(0)
            val dd = items1?.get(1)
            val month = items1?.get(2)
            binding.day.text = day
            binding.date.text = dd
            binding.month.text = month
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}