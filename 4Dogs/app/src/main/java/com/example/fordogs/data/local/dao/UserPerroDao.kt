package com.example.fordogs.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fordogs.data.local.entity.userPerro

@Dao
interface UserPerroDao {

    @Query("SELECT * FROM userPerro")
    suspend fun getInfoForUserPerro(): userPerro

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userPerro: userPerro)

    @Update
    suspend fun update(userPerro: userPerro)

    @Delete
    suspend fun delete(userPerro: userPerro)
    
}