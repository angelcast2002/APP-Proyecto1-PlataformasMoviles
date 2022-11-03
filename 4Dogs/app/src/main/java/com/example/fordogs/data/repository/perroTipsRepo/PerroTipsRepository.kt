package com.example.fordogs.data.repository.perroTipsRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.entity.PerroTips

interface PerroTipsRepository {

    suspend fun getPerroTips(): Resource<List<PerroTips>?>
}