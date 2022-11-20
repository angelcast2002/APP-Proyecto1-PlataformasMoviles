package com.example.fordogs.data.repository.perroTipsRepo

import com.example.fordogs.data.Resource
import com.example.fordogs.data.local.dao.perroTips.PerroTipsDao
import com.example.fordogs.data.local.entity.PerroTips
import com.example.fordogs.data.remote.DogsApi
import com.example.fordogs.data.remote.dto.mapToEntity

class PerroTipsRepsitoryImpl(
    private val perroTipsDao: PerroTipsDao,
    private val api: DogsApi
) : PerroTipsRepository {
    override suspend fun getPerroTips(name:String): Resource<PerroTips> {
        val localTips = perroTipsDao.getPerroTips()
        return try {
            if (localTips == null) {
                val remoteTips = api.getDogsTips(name)
                if (remoteTips == null) {
                    Resource.Error(message = "No hay información de la raza")
                } else {
                    val mappedPerroTips = remoteTips.mapToEntity()
                    Resource.Success(data = mappedPerroTips)
                }
            } else {
                Resource.Success(data = localTips)
            }
        }catch (e: Exception) {
            Resource.Error(message = "Error inesperado")
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