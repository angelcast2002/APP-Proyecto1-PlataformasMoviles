package com.example.fordogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Event(
    val title: String,
    val content: String,
    val date: String,
    val time: String,
    @PrimaryKey val id: Int? = null
)