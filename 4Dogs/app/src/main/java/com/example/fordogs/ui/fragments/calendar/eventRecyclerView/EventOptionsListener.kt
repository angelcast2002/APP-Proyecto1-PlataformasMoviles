package com.example.fordogs.ui.fragments.calendar.eventRecyclerView

interface EventOptionsListener {

    fun deleteEventFromId(eventId: Int)

    fun updateEventFromId(eventId: Int)

    fun completeEventFromId(eventId: Int)

}