package com.example.fordogs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fordogs.data.local.dao.perroTips.PerroTipsDao
import com.example.fordogs.data.local.entity.PerroTips


@Database(
    entities = [PerroTips::class],
    version = 1
)

abstract class PerroTipsDb: RoomDatabase() {
    abstract fun perroTipsDao(): PerroTipsDao
}