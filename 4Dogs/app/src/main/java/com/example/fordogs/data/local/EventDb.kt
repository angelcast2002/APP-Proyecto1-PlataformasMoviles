package com.example.fordogs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fordogs.data.local.dao.events.EventDao
import com.example.fordogs.data.local.entity.Event


@Database(
    entities = [Event::class],
    version = 1
)
abstract class EventDb : RoomDatabase() {
    abstract fun eventDao(): EventDao
}