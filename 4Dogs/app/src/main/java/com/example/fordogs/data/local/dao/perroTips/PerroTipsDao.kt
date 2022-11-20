package com.example.fordogs.data.local.dao.perroTips

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fordogs.data.local.entity.PerroTips

@Dao
interface PerroTipsDao {

    @Query("SELECT * FROM PerroTips")
    suspend fun getPerroTips(): PerroTips

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPerroTips(data: PerroTips)
}