package com.example.fordogs.data.repository.perroTipsRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.perroTips.PerroTipsDao
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.remote.DogsApi
import com.example.fordogs.data.repository.userPerroRepo.UserPerroRepository

class PerroTipsRepsitoryImpl(
    private val perroTipsDao: PerroTipsDao,
    private val api: DogsApi
) : PerroTipsRepository {
    override suspend fun getPerroTips(): Resource<List<PerroTips>?> {
        TODO("Not yet implemented")
    }

    override suspend fun savePerroTips(data: List<PerroTips>?): Resource<String> {
        TODO("Not yet implemented")
    }

}