package com.example.fordogs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fordogs.data.local.dao.Events.EventDao
import com.example.fordogs.data.local.entity.Event


@Database(
    entities = [Event::class],
    version = 1
)
abstract class EventDB: RoomDatabase() {
    abstract val eventDao: EventDao
}
