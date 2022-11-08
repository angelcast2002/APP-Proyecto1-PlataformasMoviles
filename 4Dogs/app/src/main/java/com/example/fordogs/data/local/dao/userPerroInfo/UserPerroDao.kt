package com.example.fordogs.data.local.dao.userPerroInfo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.fordogs.data.local.entity.UserPerro

@Dao
interface UserPerroDao {

    @Query("SELECT * FROM UserPerro")
    suspend fun getInfoForUserPerro(): UserPerro

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userPerro: UserPerro)

    @Update
    suspend fun update(userPerro: UserPerro)

    @Delete
    suspend fun delete(userPerro: UserPerro)
    
}