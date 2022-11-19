package com.example.fordogs.data.local.dao.perroTips

import androidx.room.*
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.local.entity.UserPerro

@Dao
interface PerroTipsDao {

    @Query("SELECT * FROM PerroTips")
    suspend fun getPerroTips(): PerroTips

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPerroTips(data: PerroTips)

    @Query("DELETE FROM PerroTips")
    suspend fun deleteAll()
}