package com.example.fordogs.data.repository.perroTipsRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.local.entity.UserPerro

interface PerroTipsRepository {

    suspend fun getPerroTips(name: String): Resource<PerroTips>
    suspend fun savePerroTips(data: PerroTips)
    suspend fun logOut()
}