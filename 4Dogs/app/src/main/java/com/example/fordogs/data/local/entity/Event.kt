package com.example.fordogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) var eventId: Int = 0,
    var eventTitle: String?,
    var eventDate: String?,
    var eventDescription: String?,
    var isComplete: Boolean = false,
    var lastAlarm: String?,
    var event:String?,
    )