package com.example.fordogs.data.repository.perroTipsRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.perroTips.PerroTipsDao
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.local.entity.UserPerro
import com.example.fordogs.data.remote.DogsApi
import com.example.fordogs.data.remote.dto.mapToEntity
import com.example.fordogs.data.repository.perroTipsRepo.TipsRepoImplConstants.Companion.MENSAJE_ERROR

class PerroTipsRepsitoryImpl(
    private val perroTipsDao: PerroTipsDao,
    private val api: DogsApi
) : PerroTipsRepository {
    override suspend fun getPerroTips(name:String): Resource<PerroTips> {
        val localTips = perroTipsDao.getPerroTips()
        return try {
            if (localTips == null || localTips.name.lowercase() != name.lowercase()) {
                val remoteTips = api.getDogsTips(name)[0]
                if (remoteTips == null) {
                    Resource.Error(message = MENSAJE_ERROR)
                } else {
                    val mappedPerroTips = remoteTips.mapToEntity()
                    Resource.Success(data = mappedPerroTips)
                }
            } else {
                Resource.Success(data = localTips)
            }
        }catch (e: Exception) {
            Resource.Error(message = MENSAJE_ERROR)
        }
    }

    override suspend fun savePerroTips(data: PerroTips){
        try {
            perroTipsDao.insertAllPerroTips(data)
        } catch (ex: Exception) {
            return println(ex)
        }
    }

    override suspend fun logOut() {
         try {
            perroTipsDao.deleteAll()

        } catch (ex: Exception) {
            return println(ex)
        }
    }

}