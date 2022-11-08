package com.example.fordogs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fordogs.data.local.dao.userPerroInfo.UserPerroDao
import com.example.fordogs.data.local.entity.UserPerro

@Database(
    entities = [UserPerro::class],
    version = 1
)
abstract class UserPerroDb: RoomDatabase() {
    abstract fun userPerroDao(): UserPerroDao
}